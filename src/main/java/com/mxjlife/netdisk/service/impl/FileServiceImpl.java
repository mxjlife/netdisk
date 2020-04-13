package com.mxjlife.netdisk.service.impl;

import com.mxjlife.netdisk.common.cache.GuavaCache;
import com.mxjlife.netdisk.common.constant.Cons;
import com.mxjlife.netdisk.common.util.BuilderUtils;
import com.mxjlife.netdisk.mapper.FileInfoMapper;
import com.mxjlife.netdisk.pojo.nd.FileChunkParams;
import com.mxjlife.netdisk.pojo.nd.FileInfoPO;
import com.mxjlife.netdisk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/4 21:47
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public boolean writeChunkFile(FileChunkParams fileParams) {
        if(!fileParams.isBoolchunk()){
            // 未分块
            return writeSingleFile(fileParams);
        }
        // 分块上传
        if(fileParams.getChunk() == -1){
            // 合并文件
            mergeFile(fileParams);
        }else{
            // 上传块
            writeTmpFile(fileParams);
        }
        return true;
    }

    /**
     * 单个文件上传
     */
    private boolean writeSingleFile(FileChunkParams fileParams) {
        log.info("文件[{}]开始上传", fileParams.getFile().getOriginalFilename());
        FileInfoPO fileInfo = null;
        try {
            fileInfo = BuilderUtils.getFileInfoPO(fileParams);
        } catch (IOException e) {
            log.error("文件解析失败", e);
            return false;
        }
        try {
            String filePath = fileInfo.getFilePath() + fileInfo.getUuid();
            File file = new File(filePath);
            if(!file.getParentFile().exists()){
                file.mkdirs();
            }
            fileParams.getFile().transferTo(file);
            // 校验写入结果的MD5
//            boolean b = checkMd5(file, fileParams.getFileMd5());
//            if(b){
                fileInfoMapper.insert(fileInfo);
//            }
//            log.info("文件[{}],写入{}", fileInfo.getFileName(), b?"成功":"失败");
            log.info("文件[{}],写入{}", fileInfo.getFileName(), "成功");
            return true;
        } catch (IOException e) {
            log.error("文件[{}]写入失败{}", fileInfo.getFileName(), e);
        }
        return false;
    }


    /**
     * 分块文件写入临时文件夹
     */
    private boolean writeTmpFile(FileChunkParams fileParams){
        log.info("文件分块上传，文件[{}],块[{}]开始上传", fileParams.getFile().getOriginalFilename(), fileParams.getChunk());
        String rootPath = GuavaCache.get(Cons.FILE_ROOT_PATH_KEY);
        MultipartFile inputFile = fileParams.getFile();
        String path = rootPath + File.separator + Cons.DIR_FILE_TMP_DIR + File.separator + fileParams.getFileMd5() + File.separator + fileParams.getChunk();
        try {
            File file = new File(path);
            if(!file.getParentFile().exists()){
                file.mkdirs();
            }else {
                if(file.exists()){
                    FileInputStream diskIs = FileUtils.openInputStream(file);
                    String md5 = DigestUtils.md5Hex(diskIs);
                    diskIs.close();
                    if(Objects.equals(fileParams.getChunkMd5(), md5)){
                        return true;
                    }else{
                        file.delete();
                    }
                }
            }
//            FileUtils.copyInputStreamToFile(inputFile.getInputStream(), file);
            fileParams.getFile().transferTo(file);
            // 校验写入结果的MD5
//            boolean b = checkMd5(file, fileParams.getFileMd5());
            log.info("文件[{}],块[{}]写入完成", fileParams.getFile().getOriginalFilename(), fileParams.getChunk());
            if(fileParams.getChunk() == fileParams.getChunks()-1){
                mergeFile(fileParams);
            }
            return true;
        } catch (IOException e) {
            log.error("文件[{}],块[{}]写入失败{}", inputFile.getOriginalFilename(), fileParams.getChunk(), e);
        }
        return false;
    }

    /**
     * 合并分块文件
     */
    private boolean mergeFile(FileChunkParams fileParams){
        log.info("开始合并文件[{}] ->{}", fileParams.getFile().getOriginalFilename(), fileParams);
        String rootPath = GuavaCache.get(Cons.FILE_ROOT_PATH_KEY);
        FileInfoPO fileInfo = null;
        try {
            fileInfo = BuilderUtils.getFileInfoPO(fileParams);
        } catch (IOException e) {
            log.error("文件解析失败", e);
            return false;
        }
        String filePath = fileInfo.getFilePath() + fileInfo.getUuid();
        String tmpDirPath = rootPath + File.separator + Cons.DIR_FILE_TMP_DIR + File.separator + fileParams.getFileMd5();
        try {
            File resFile = new File(filePath);
            if(!resFile.getParentFile().exists()){
                resFile.mkdirs();
            }
            File tmpDir = new File(tmpDirPath);
            if(!tmpDir.exists() || !tmpDir.isDirectory()){
                log.error("文件[{}]未找到分片文件", fileInfo.getFileName());
                return false;
            }
            File[] tmpFiles = tmpDir.listFiles();
            if(tmpFiles == null){
                log.error("文件[{}]未找到分片文件", fileInfo.getFileName());
                return false;
            }
            Arrays.sort(tmpFiles, Comparator.comparingInt(file -> Integer.parseInt(file.getName())));
            FileOutputStream resFos = new FileOutputStream(resFile, true);
            for (File tmpFile : tmpFiles) {
                // 合并到最终文件
                FileUtils.copyFile(tmpFile, resFos);
                resFos.flush();
            }
            resFos.close();
            // 校验写入结果的MD5
            boolean b = checkMd5(resFile, fileParams.getFileMd5());
            if(b){
                fileInfoMapper.insert(fileInfo);
                FileUtils.deleteDirectory(tmpDir);
            }
            log.info("文件[{}] uuid[{}],合并{}", fileInfo.getFileName(), fileInfo.getUuid(), b?"成功":"失败");
            return b;
        } catch (Exception e) {
            log.error("文件[{}],合并失败{}", fileInfo.getFileName(), e);
        }
        return false;
    }


    /**
     * 校验文件 MD5
     * @param file
     * @param reqMd5
     * @return
     * @throws IOException
     */
    private boolean checkMd5(File file, String reqMd5) throws IOException {
        FileInputStream resIs = FileUtils.openInputStream(file);
        String md5 = DigestUtils.md5Hex(resIs);
        resIs.close();
        if(Objects.equals(reqMd5, md5)){
            // 上传成功
            return true;
        } else {
            file.delete();
            return false;
        }
    }
}

package com.mxjlife.netdisk.common.util;

import com.mxjlife.netdisk.common.cache.GuavaCache;
import com.mxjlife.netdisk.common.constant.Cons;
import com.mxjlife.netdisk.pojo.nd.FileChunkParams;
import com.mxjlife.netdisk.pojo.nd.FileInfoPO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/5 18:23
 */
public class BuilderUtils {

    /**
     * 获取文件信息
     */
    public static FileInfoPO getFileInfoPO(FileChunkParams fileParams) throws IOException {
        String rootPath = GuavaCache.get(Cons.FILE_ROOT_PATH_KEY);
//        MultipartFile inputFile = fileParams.getFile();
//        InputStream inputStream = inputFile.getInputStream();
        String uuid = NdUtils.uuid();
//        Tika tika = new Tika();
//        String typeInfo = tika.detect(inputStream);
//        inputStream.close();
        String path = rootPath + File.separator + Cons.DIR_FILE_DIR + File.separator;
        FileInfoPO fileInfo = new FileInfoPO();
        fileInfo.setUuid(uuid);
//        fileInfo.setFileType(NdUtils.getFileType(typeInfo));
//        fileInfo.setFileInfo(typeInfo);
        fileInfo.setFileName(fileParams.getFileName());
        fileInfo.setFileExtension(FilenameUtils.getExtension(fileInfo.getFileName()));
        fileInfo.setFilePath(path);
        fileInfo.setFileSize(fileParams.getFileSize());
        fileInfo.setFileMd5(fileParams.getFileMd5());
        fileInfo.setCreateTime(DateFormatUtils.format(new Date(), "YYYY-MM-dd HH:mm:ss"));
        fileInfo.setModifyTime(fileInfo.getCreateTime());
        return fileInfo;
    }
}

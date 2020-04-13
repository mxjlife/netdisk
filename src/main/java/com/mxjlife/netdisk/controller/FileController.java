package com.mxjlife.netdisk.controller;

import com.mxjlife.netdisk.common.enums.SysCodeEnum;
import com.mxjlife.netdisk.pojo.base.BaseResult;
import com.mxjlife.netdisk.pojo.nd.FileChunkParams;
import com.mxjlife.netdisk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: 操作文件
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 10:27
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping(value = "/upload")
    public BaseResult upload(@RequestParam(value = "file",required = false) MultipartFile file,
                             @RequestParam("name") String name,
                             @RequestParam("chunk") int chunk,
                             @RequestParam("chunks") int chunks,
                             @RequestParam("size") long size,
                             @RequestParam("md5") String md5){
        FileChunkParams fileParams = new FileChunkParams();
        fileParams.setChunk(chunk);
        fileParams.setChunks(chunks);
        fileParams.setFileMd5(md5);
        fileParams.setFileSize(size);
        fileParams.setFileName(name);
        fileParams.setFile(file);
        fileParams.setBoolchunk(chunks > 1);

        log.info("文件上传-> {}", fileParams);
        BaseResult resp = new BaseResult();
        boolean b = fileService.writeChunkFile(fileParams);
        if(!b){
            resp.setCode(SysCodeEnum.FILE_UPLOAD_ERROR.getCode());
            resp.setMsg(SysCodeEnum.FILE_UPLOAD_ERROR.getMsg());
        }
        return resp;
    }




}

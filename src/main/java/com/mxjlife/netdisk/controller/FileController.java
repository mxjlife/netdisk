package com.mxjlife.netdisk.controller;

import com.mxjlife.netdisk.common.enums.SysCodeEnum;
import com.mxjlife.netdisk.pojo.base.BaseResult;
import com.mxjlife.netdisk.pojo.nd.FileChunkParams;
import com.mxjlife.netdisk.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * description: 操作文件
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 10:27
 */
@RestController
@CrossOrigin
@Slf4j
public class FileController {
    @Autowired
    FileService fileService;

    /**
     * 文件上传
     */
    @PostMapping(value = "/upload")
    public BaseResult upload(@RequestBody FileChunkParams fileParams){
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

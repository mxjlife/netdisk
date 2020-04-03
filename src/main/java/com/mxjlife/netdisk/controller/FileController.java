package com.mxjlife.netdisk.controller;

import com.mxjlife.netdisk.pojo.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/upload")
    public ResponseDTO<Object> upload(){
        ResponseDTO<Object> resp = new ResponseDTO<>();


        return resp;
    }

}

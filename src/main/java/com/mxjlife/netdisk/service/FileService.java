package com.mxjlife.netdisk.service;

import com.mxjlife.netdisk.pojo.nd.FileChunkParams;

/**
 * description: 文件操作
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/4 21:36
 */
public interface FileService {

    /**
     * 上传文件写入
     */
    boolean writeChunkFile(FileChunkParams fileParams);



}

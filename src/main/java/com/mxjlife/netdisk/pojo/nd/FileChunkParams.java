package com.mxjlife.netdisk.pojo.nd;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * description:文件分块上传信息
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 11:11
 */
@Data
@ToString(exclude={"file"})
public class FileChunkParams {

    /**
     * 是否分块, true分块上传, false未分块
     */
    private boolean boolchunk;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 块MD5
     */
    private String chunkMd5;
    /**
     * 文件块序号, 从0开始
     */
    private int chunk;
    /**
     * 块数量
     */
    private int chunks;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件
     */
    private MultipartFile file;
}
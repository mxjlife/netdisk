package com.mxjlife.netdisk.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * description:文件分块上传信息
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 11:11
 */
@Data
public class FileBlockParams {

    /**
     * 是否分块
     */
    private int block;
    /**
     * 当前块名称
     */
    private String blockName;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 块MD5
     */
    private String blockMd5;
    /**
     * 块大小
     */
    private String blockSize;
    /**
     * 文件块序号
     */
    private String blockNo;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 文件
     */
    private MultipartFile file;
}
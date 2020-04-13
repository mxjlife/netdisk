package com.mxjlife.netdisk.pojo.nd;

import lombok.Data;
import java.util.Date;

/**
 * description: 文件信息实体类
 * email realmxj@163.com
 * @author: mxj
 * date 2020-04-05 18:32:07
 * @version: V1.0
 */
@Data
public class FileInfoPO {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 文件类型,1图片,2视频,3音频,4文档,5压缩包,6应用,99未知
     */
    private Integer fileType;

    /**
     * 文件其他信息,json
     */
    private String fileInfo;

    /**
     * 文件标签
     */
    private String fileTags;

    /**
     * 原始文件名
     */
    private String fileName;

    /**
     * 文件扩展名
     */
    private String fileExtension;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小,kb
     */
    private Long fileSize;

    /**
     * 文件MD5
     */
    private String fileMd5;

    /**
     * 状态:1有效,2删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


}

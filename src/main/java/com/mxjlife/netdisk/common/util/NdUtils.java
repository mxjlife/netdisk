package com.mxjlife.netdisk.common.util;

import com.mxjlife.netdisk.common.enums.FileType;

import java.util.UUID;

/**
 * description: 一些公共方法
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/4 22:56
 */
public class NdUtils {

    /**
     * 获取uuid
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 获取文件类型
     */
    public static Integer getFileType(String typeInfo){
        String[] split = typeInfo.split("/");
        return FileType.getTypeCode(split[0]);
    }
}

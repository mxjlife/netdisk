package com.mxjlife.netdisk.common.enums;

import lombok.Getter;
import java.util.Objects;

@Getter
public enum FileType {

    // 文件类型
    TYPE_IMAGE(1, "image"),
    TYPE_VIDEO(2, "video"),
    TYPE_AUDIO(3, "audio"),
    TYPE_TEXT(4, "text"),
    TYPE_APP(5, "application"),
    TYPE_DOC(6, "doc"),
    TYPE_ZIP(7, "zip"),
    TYPE_OTHER(99, "other"),


    ;



    /**
     * 编码
     */
    private Integer type;

    /**
     * 名称
     */
    private String name;

    FileType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }


    public static String getTypeName(Integer code){
        for (FileType value : FileType.values()) {
            if(Objects.equals(value.getType(), code)){
                return value.getName();
            }
        }
        return null;
    }
    public static Integer getTypeCode(String name){
        for (FileType value : FileType.values()) {
            if(Objects.equals(value.getName(), name)){
                return value.getType();
            }
        }
        return 0;
    }

}

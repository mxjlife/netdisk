package com.mxjlife.netdisk.pojo.base;

import lombok.Data;

import java.util.Date;

/**
 * description 配置信息
 *
 * @author mxj
 * email mxjlife@163.com
 * date 2019/1/2 8:58
 */
@Data
public class ConfigInfo {

    // 主键
    private Integer id;
    // 配置所属于的系统
    private String system;
    // 配置所属于的功能
    private String keyspace;
    // 配置名
    private String key;
    // 配置值
    private String value;
    //  配置描述
    private String desc;
    // 配置信息状态
    private Integer status;
    // 创建用户
    private String createUser;
    // 修改用户
    private String modifyUser;
    // 备注
    private String remark;
    // 更新时间
    private Date updateTime;
    // 创建时间
    private Date createTime;

}

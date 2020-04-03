package com.mxjlife.netdisk.common.constant;

import lombok.Getter;

/**
 * description 系统中用到的一些状态码及状态描述
 * author mxj
 * email mxjlife@163.com
 * date 2019/1/2 11:57
 */
@Getter
public enum SysCodeEnum {

    /**
     * 操作成功！
     */
    RESP_SUCCESS(200, "操作成功！"),

    /**
     * 操作异常！
     */
    RESP_ERROR(500, "操作异常！"),

    /**
     * 退出成功！
     */
    RESP_LOGOUT(200, "退出成功！"),

    /**
     * 请先登录！
     */
    RESP_UNAUTHORIZED(401, "请先登录！"),

    /**
     * 暂无权限访问！
     */
    RESP_ACCESS_DENIED(403, "权限不足！"),

    /**
     * 请求不存在！
     */
    RESP_REQUEST_NOT_FOUND(404, "请求不存在！"),

    /**
     * 请求方式不支持！
     */
    RESP_HTTP_BAD_METHOD(405, "请求方式不支持！"),

    /**
     * 请求异常！
     */
    RESP_BAD_REQUEST(400, "请求异常！"),

    /**
     * 参数不匹配！
     */
    RESP_PARAM_NOT_MATCH(400, "参数不匹配！"),

    /**
     * 参数不能为空！
     */
    RESP_PARAM_NOT_NULL(400, "参数不能为空！"),

    /**
     * 当前用户已被锁定，请联系管理员解锁！
     */
    RESP_USER_DISABLED(403, "当前用户已被锁定，请联系管理员解锁！"),

    /**
     * 用户名或密码错误！
     */
    RESP_USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误！"),

    /**
     * token 已过期，请重新登录！
     */
    RESP_TOKEN_EXPIRED(5002, "token 已过期，请重新登录！"),

    /**
     * token 解析失败，请尝试重新登录！
     */
    RESP_TOKEN_PARSE_ERROR(5002, "token 解析失败，请尝试重新登录！"),

    /**
     * 当前用户已在别处登录，请尝试更改密码或重新登录！
     */
    RESP_TOKEN_OUT_OF_CTRL(5003, "当前用户已在别处登录，请尝试更改密码或重新登录！"),

    /**
     * 无法手动踢出自己，请尝试退出登录操作！
     */
    RESP_KICKOUT_SELF(5004, "无法手动踢出自己，请尝试退出登录操作！");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    SysCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

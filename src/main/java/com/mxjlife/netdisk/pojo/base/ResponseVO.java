package com.mxjlife.netdisk.pojo.base;

import com.mxjlife.netdisk.common.enums.SysCodeEnum;
import lombok.Data;

/**
 * description 返回参数
 *
 * @author mxj
 * email mxjlife@163.com
 * date 2019/1/2 11:40
 */
@Data
public class ResponseVO<T> extends BaseResult {
    private T data;

    public ResponseVO() {
        this.code = SysCodeEnum.RESP_SUCCESS.getCode();
        this.msg = SysCodeEnum.RESP_SUCCESS.getMsg();
    }

    public ResponseVO(SysCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public ResponseVO(SysCodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}

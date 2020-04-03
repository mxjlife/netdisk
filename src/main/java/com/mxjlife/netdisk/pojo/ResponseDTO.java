package com.mxjlife.netdisk.pojo;

import com.mxjlife.netdisk.common.constant.SysCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description 返回参数
 *
 * @author mxj
 * email mxjlife@163.com
 * date 2019/1/2 11:40
 */
@Data
public class ResponseDTO<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResponseDTO() {
        this.code = SysCodeEnum.RESP_SUCCESS.getCode();
        this.msg = SysCodeEnum.RESP_SUCCESS.getMsg();
    }

    public ResponseDTO(SysCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public ResponseDTO(SysCodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public ResponseDTO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
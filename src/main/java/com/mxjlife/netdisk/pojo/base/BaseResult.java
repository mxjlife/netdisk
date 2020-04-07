package com.mxjlife.netdisk.pojo.base;

import com.mxjlife.netdisk.common.enums.SysCodeEnum;
import lombok.Data;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/4 21:38
 */
@Data
public class BaseResult {

    public static final Integer SUCCESS_CODE = SysCodeEnum.RESP_SUCCESS.getCode();

    Integer code = SUCCESS_CODE;
    String msg = "success";

}

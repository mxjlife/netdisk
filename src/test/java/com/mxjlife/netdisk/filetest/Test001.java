package com.mxjlife.netdisk.filetest;

import com.mxjlife.netdisk.pojo.ResponseDTO;
import org.junit.Test;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/3 10:51
 */
public class Test001 {

    @Test
    public void test001(){
        ResponseDTO<Object> success = ResponseDTO.success;
        System.out.println(success);
        System.out.println(success);
        ResponseDTO<Object> success2 = ResponseDTO.success;
        System.out.println(success2);


    }


}

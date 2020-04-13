package com.mxjlife.netdisk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 * author mxj
 * email mengxiangjie@hualala.com
 * date 2020/4/7 17:58
 */
@CrossOrigin
@Slf4j
@Controller
public class ViewController {

    @RequestMapping(value = {"", "/"})
    public String index(){
        return "index";
    }
}

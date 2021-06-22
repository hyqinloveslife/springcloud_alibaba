package com.hyqin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/21  16:01
 */
@RestController
public class TestController {

    @GetMapping("/test1")
    public String test1(){
        return "success";
    }
}

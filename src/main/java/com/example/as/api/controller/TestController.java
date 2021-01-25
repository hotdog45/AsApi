package com.example.as.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@Api(value = "测试类")
public class TestController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public Object hello() {
        return "hello spring~!!持续集成OK~";
    }
}

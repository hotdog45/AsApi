package com.example.as.api.controller;

import com.example.as.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供给前端使用的接口对应的方法
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public Object registration(@RequestParam(value = "username") String username
            , @RequestParam(value = "password") String password
            , @RequestParam(value = "imoocId") String imoocId
            , @RequestParam(value = "orderId") String orderId) {

        String test = "username:" + username + ",password:" + password
                + ",imoocId:" + imoocId + ",orderId:" + orderId;

        userService.addUser(username,bCryptPasswordEncoder.encode(password),imoocId,orderId);
        System.out.println(test);
        return test;

    }
}

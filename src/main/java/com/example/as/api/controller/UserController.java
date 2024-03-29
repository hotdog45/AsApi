package com.example.as.api.controller;

import com.example.as.api.config.NeedLogin;
import com.example.as.api.entity.ResponseEntity;
import com.example.as.api.entity.UserEntity;
import com.example.as.api.service.UserService;
import com.example.as.api.uitl.DataUtil;
import com.example.as.api.uitl.ResponseCode;
import com.example.as.api.uitl.UserRedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 提供给前端使用的接口对应的方法
 */
@RestController
@RequestMapping(value = "/user")

@Api(tags = "用户中心")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@RequestParam(value = "username") @ApiParam("账号或手机号") String username
            , @RequestParam(value = "password") @ApiParam("密码") String password
            , @RequestParam(value = "imoocId") @ApiParam("慕课网id") String imoocId
            , @RequestParam(value = "orderId") @ApiParam("订单号") String orderId) {
        userService.addUser(username, bCryptPasswordEncoder.encode(password), imoocId, orderId);
        return ResponseEntity.successMessage("注册成功!");

    }

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestParam(value = "username") @ApiParam("账号或手机号") String username
            , @RequestParam(value = "password") @ApiParam("密码") String password, HttpServletRequest request) {
        List<UserEntity> list = userService.findUser(username);
        if (list == null || list.isEmpty()) {
            return ResponseEntity.of(ResponseCode.RC_ACCOUNT_INVALID);
        }
        UserEntity userEntity = null;
        for (UserEntity entity : list) {
            //判断密码是否相等
            if (bCryptPasswordEncoder.matches(password, entity.pwd)) {
                userEntity = entity;
                break;
            }

        }
        if (userEntity == null) {
            return ResponseEntity.of(ResponseCode.RC_PWD_INVALID);
        }
        if ("1".equals(userEntity.forbid)) {
            return ResponseEntity.of(ResponseCode.RC_USER_FORBID);
        }
        UserRedisUtil.addUser(redisTemplate, request.getSession(), userEntity);

        return ResponseEntity.success(UserRedisUtil.getKey(request.getSession())).setMessage("登录成功!");

    }


    @NeedLogin
    @ApiOperation(value = "登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity login(HttpServletRequest request) {
        UserRedisUtil.removeUser(redisTemplate, request.getSession());

        return ResponseEntity.successMessage("退出成功~");
    }

    @ApiOperation(value = "用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "index") @ApiParam("第几页") int index
            ,@RequestParam(value = "pageSize") @ApiParam("每页数量") int pageSize
            ,HttpServletRequest request) {
        PageHelper.startPage(index,pageSize);
        List<UserEntity> list = userService.findAllUser();
        return ResponseEntity.success(DataUtil.getPageData(list));

    }

    @ApiOperation(value = "用户管理")
    @RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@ApiParam("用户id") @PathVariable String uid
            ,@RequestParam(value = "forbid") @ApiParam("是否禁止") String forbid
            ,HttpServletRequest request) {
        userService.updateUser(uid,forbid);
        return ResponseEntity.successMessage("修改成功!");

    }
}

package com.example.as.api.service;

import com.example.as.api.entity.UserEntity;
import com.example.as.api.mapper.UserMapper;
import com.example.as.api.uitl.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public void addUser(String username,String password,String imoocId,String orderId){
        userMapper.addUser(username,password,imoocId,orderId, DateUtil.currentDate());
    }
    public List<UserEntity> findUser(String userName){
       return userMapper.findUser(userName);
    }
}

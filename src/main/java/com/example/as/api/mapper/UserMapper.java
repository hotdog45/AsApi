package com.example.as.api.mapper;

import org.springframework.stereotype.Repository;


/**
 * 未查询数据库配置的映射关系类
 * 数据持久层
 */
@Repository
public interface UserMapper {
    void addUser(String userName,String password,String imoocId,String orderId,String createTime);
}

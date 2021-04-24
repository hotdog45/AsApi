package com.example.as.api.mapper;

import com.example.as.api.entity.CityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 未查询数据库配置的映射关系类
 * 数据持久层
 */
@Repository
public interface CityMapper {
    List<CityEntity> findAllCity();

}

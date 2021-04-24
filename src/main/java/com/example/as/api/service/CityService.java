package com.example.as.api.service;

import com.example.as.api.entity.CityEntity;
import com.example.as.api.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<CityEntity> findAllCity(){
        return cityMapper.findAllCity();
    }
}

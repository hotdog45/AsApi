package com.example.as.api.controller;

import com.example.as.api.entity.CityEntity;
import com.example.as.api.entity.ResponseEntity;
import com.example.as.api.service.CityService;
import com.example.as.api.uitl.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
@Api(tags = "City")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "城市列表")
    public ResponseEntity getCityList(){
        List<CityEntity> list = cityService.findAllCity();
        return ResponseEntity.success(DataUtil.getPageData(list));
    }

}

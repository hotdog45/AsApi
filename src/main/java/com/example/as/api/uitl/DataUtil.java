package com.example.as.api.uitl;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
    /**
     * 获取分页数据
     * @param list
     * @param <T>
     * @return
     */
    public static <T>Map<String,Object> getPageData(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        Map<String,Object> data = new HashMap<>();
        data.put("list",pageInfo.getList());
        data.put("total",pageInfo.getTotal());
        return data;
    }

}

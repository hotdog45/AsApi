package com.example.as.api.uitl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    public static String toJsonString(Object o){

        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static <T>T fromJson(String json,Class<T> tClass){
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            mapper.readValue(json,tClass);
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
}

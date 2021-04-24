package com.example.as.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityEntity {
    public String id ;
    public String pid ;
    public String name ;
    public int level ;
    public int visible ;
    public String displayorder ;

}

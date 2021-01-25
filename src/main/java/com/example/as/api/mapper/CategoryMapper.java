package com.example.as.api.mapper;

import com.example.as.api.entity.CategoryEntity;
import com.example.as.api.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<CategoryEntity> getCategoryList();

    void addCategory(String categoryName, String createTime);
    void deleteCategory(String id);

}

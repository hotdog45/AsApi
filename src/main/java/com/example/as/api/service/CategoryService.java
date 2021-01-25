package com.example.as.api.service;

import com.example.as.api.entity.CategoryEntity;
import com.example.as.api.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryEntity> findCategoryList(){
        return categoryMapper.getCategoryList();
    }

    public void addCategory(String categoryName, String createTime){
        categoryMapper.addCategory(categoryName,createTime);
    }

    public void deleteCategory(String id) {
        categoryMapper.deleteCategory(id);
    }
}

package com.example.as.api.controller;

import com.example.as.api.entity.CategoryEntity;
import com.example.as.api.entity.ResponseEntity;
import com.example.as.api.entity.UserEntity;
import com.example.as.api.service.CategoryService;
import com.example.as.api.uitl.DataUtil;
import com.example.as.api.uitl.DateUtil;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
@Api(tags = "商品类别")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @ApiOperation(value = "商品类别列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity category(@RequestParam(value = "index") @ApiParam(value = "第几页",defaultValue = "1") int index
            , @RequestParam(value = "pageSize" ,defaultValue = "10") @ApiParam("每页数量") int pageSize){

        PageHelper.startPage(index,pageSize);
        List<CategoryEntity> list = categoryService.findCategoryList();
        return ResponseEntity.success(DataUtil.getPageData(list));
    }

    @ApiOperation(value = "添加商品")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity addCategory(@RequestParam(value = "name") @ApiParam(value = "分类名") String name
            ){
        categoryService.addCategory(name, DateUtil.currentDate());
        return ResponseEntity.successMessage("添加成功~");

    }

    @ApiOperation(value = "删除商品")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@RequestParam(value = "id") @ApiParam(value = "分类id") String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.successMessage("删除成功~");
    }

}

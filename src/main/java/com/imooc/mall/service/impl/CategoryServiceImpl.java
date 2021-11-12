package com.imooc.mall.service.impl;

import com.imooc.mall.consts.MallConst;
import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import com.imooc.mall.service.ICategoryService;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryVo> categoryVoList = new ArrayList<>();
        //查出parent—id=0
        for (Category category : categories){
            if (category.getParentId().equals(MallConst.ROOT_PARENT_ID)){
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category,categoryVo);
                categoryVoList.add(categoryVo);
            }
        }
        categoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
        //查询子目录
        findSubCategory(categoryVoList,categories);

        return ResponseVo.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id,resultSet,categories);
    }
    private void findSubCategoryId(Integer id, Set<Integer> resultSet , List<Category> categories) {
        for (Category category : categories){
            if (category.getParentId().equals(id)){
                resultSet.add(category.getId());
                findSubCategoryId(category.getId(),resultSet,categories);//传的父级id
            }
        }
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories){
        if (categoryVoList == null)//可以不写，为空自动停止
            return;
        for (CategoryVo categoryVo : categoryVoList){
            List<CategoryVo> subCategoryVolist = new ArrayList<>();
            for (Category category : categories){
                if (categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subCategoryVo = category2CatgoryVo(category);
                    subCategoryVolist.add(subCategoryVo);
                }
                subCategoryVolist.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());//降序
                categoryVo.setSubCategories(subCategoryVolist);
                findSubCategory(subCategoryVolist,categories);
            }
        }
    }
    private CategoryVo category2CatgoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}

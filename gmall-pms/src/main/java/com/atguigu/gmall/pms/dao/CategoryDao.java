package com.atguigu.gmall.pms.dao;

import com.atguigu.gmall.pms.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author dadasheng
 * @email dadasheng@atguigu.com
 * @date 2020-01-04 22:39:53
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}

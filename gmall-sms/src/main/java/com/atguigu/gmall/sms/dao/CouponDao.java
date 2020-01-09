package com.atguigu.gmall.sms.dao;

import com.atguigu.gmall.sms.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author dadasheng
 * @email dadasheng@atguigu.com
 * @date 2020-01-07 19:59:15
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}

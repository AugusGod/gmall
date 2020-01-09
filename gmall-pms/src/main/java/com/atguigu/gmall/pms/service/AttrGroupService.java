package com.atguigu.gmall.pms.service;

import com.atguigu.gmall.pms.vo.GroupVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;

import java.util.List;


/**
 * 属性分组
 *
 * @author dadasheng
 * @email dadasheng@atguigu.com
 * @date 2020-01-04 22:39:54
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryByCidPage(QueryCondition queryCondition, Long cid);


    GroupVo queryById(Long gid);

    List<GroupVo> queryByCid(Long catId);

}


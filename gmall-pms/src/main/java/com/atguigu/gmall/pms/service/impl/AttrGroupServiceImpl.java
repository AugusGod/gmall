package com.atguigu.gmall.pms.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.atguigu.gmall.pms.dao.AttrAttrgroupRelationDao;
import com.atguigu.gmall.pms.dao.AttrDao;
import com.atguigu.gmall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gmall.pms.entity.AttrEntity;
import com.atguigu.gmall.pms.vo.GroupVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.Query;
import com.atguigu.core.bean.QueryCondition;

import com.atguigu.gmall.pms.dao.AttrGroupDao;
import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrDao attrDao;
    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryByCidPage(QueryCondition queryCondition, Long cid) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(queryCondition),
                new QueryWrapper<AttrGroupEntity>().eq("catelog_id",cid)
        );
        return new PageVo(page);
    }

    @Override
    public GroupVo queryById(Long gid) {
        GroupVo aroupVo = new GroupVo();
        //1根据id查询分组
        AttrGroupEntity attrGroupEntity = this.attrGroupDao.selectById(gid);
        BeanUtils.copyProperties(attrGroupEntity,aroupVo);
        //2查询分组的下的关联关系（中间表）
        List<AttrAttrgroupRelationEntity> relations
                = this.relationDao.selectList(
                new QueryWrapper<AttrAttrgroupRelationEntity>()
                        .eq("attr_group_id", gid));
        //判断关联关系的中间表是否为空，如果是空则直接进行返回
        if (CollectionUtils.isEmpty(relations)){
          return aroupVo;
        }
        aroupVo.setRelations(relations);
      //3收集分组下的所有的规格id
        List<Long> attrIds = relations.stream()
                .map(relation -> relation.getAttrId())
                .collect(Collectors.toList());
        //查询分组下的所有的规格参数
        List<AttrEntity> attrEntities = this.attrDao.selectBatchIds(attrIds);
        aroupVo.setAttrEntities(attrEntities);
        return aroupVo;


    }

    @Override
    public List<GroupVo> queryByCid(Long catId) {
       //查询所有的分组
        List<AttrGroupEntity> attrGroupEntities
                = this.list(new QueryWrapper<AttrGroupEntity>()
                .eq("catelog_id", catId));

        //查询出每组下的规格参数
        List<GroupVo> collect = attrGroupEntities
                     .stream()
                     .map(attrGroupEntity -> {
            return  this.queryById(attrGroupEntity
                     .getAttrGroupId());
                      })
                     .collect(Collectors.toList());
        return collect;
    }

}
package com.atguigu.gmall.pms.controller;

import java.util.Arrays;
import java.util.List;


import com.atguigu.core.bean.PageVo;
import com.atguigu.core.bean.QueryCondition;
import com.atguigu.core.bean.Resp;
import com.atguigu.gmall.pms.vo.GroupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.atguigu.gmall.pms.entity.AttrGroupEntity;
import com.atguigu.gmall.pms.service.AttrGroupService;




/**
 * 属性分组
 *
 * @author dadasheng
 * @email dadasheng@atguigu.com
 * @date 2020-01-04 22:39:54
 */
@Api(tags = "属性分组 管理")
@RestController
@RequestMapping("pms/attrgroup")
public class AttrGroupController {
@ApiOperation("根据三级分类id查询分组及分组下的规格参数")
@GetMapping("/withattrs/cat/{catId}")
public  Resp<List<GroupVo>> queryByCid(@PathVariable("catId") Long catId ){
      List<GroupVo> list=this.attrGroupService
              .queryByCid(catId);
      return  Resp.ok(list);
}






    @Autowired
    private AttrGroupService attrGroupService;
    @ApiOperation("根据分组id查询分组及组下的规格参数")
    @GetMapping("withattr/{gid}")
    public Resp<GroupVo> queryById(@PathVariable("gid")Long gid){
        GroupVo groupVO = this.attrGroupService.queryById(gid);
        return Resp.ok(groupVO);
}





    @ApiOperation("根据三级分类id分页查询")
    @GetMapping("{cid}")

    public Resp<PageVo> queryByCidPage(QueryCondition queryCondition,
       @PathVariable("cid") Long cid               ) {
        PageVo page = this.attrGroupService.queryByCidPage(queryCondition,cid);

        return Resp.ok(page);
    }
    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('pms:attrgroup:list')")
    public Resp<PageVo> list(QueryCondition queryCondition) {
        PageVo page = attrGroupService.queryPage(queryCondition);

        return Resp.ok(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("/info/{attrGroupId}")
    @PreAuthorize("hasAuthority('pms:attrgroup:info')")
    public Resp<AttrGroupEntity> info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        return Resp.ok(attrGroup);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('pms:attrgroup:save')")
    public Resp<Object> save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return Resp.ok(null);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('pms:attrgroup:update')")
    public Resp<Object> update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return Resp.ok(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('pms:attrgroup:delete')")
    public Resp<Object> delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return Resp.ok(null);
    }

}

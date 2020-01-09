package com.atguigu.gmall.search.repository;

import com.atguigu.gmall.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//GoodsRepository需要继承ElasticsearchRepository才能将Goods设置成索引对象
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}

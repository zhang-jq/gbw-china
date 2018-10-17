package com.spider.gbwchina.dao;

import com.spider.gbwchina.model.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductDao {

    int save(Product product);

    Product findByUrl(@Param("goodsUrl") String goodsUrl);

    int update(Product product);
}

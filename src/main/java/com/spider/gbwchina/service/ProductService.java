package com.spider.gbwchina.service;

import com.spider.gbwchina.model.Product;

public interface ProductService {

    int save(Product product);

    Product findByUrl(String goodsUrl);

    int update(Product product);
}

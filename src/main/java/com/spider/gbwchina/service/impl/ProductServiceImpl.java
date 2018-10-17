package com.spider.gbwchina.service.impl;

import com.spider.gbwchina.dao.ProductDao;
import com.spider.gbwchina.model.Product;
import com.spider.gbwchina.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Override
    public int save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product findByUrl(String goodsUrl) {
        return productDao.findByUrl(goodsUrl);
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }
}

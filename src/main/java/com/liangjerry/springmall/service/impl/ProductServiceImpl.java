package com.liangjerry.springmall.service.impl;

import com.liangjerry.springmall.dao.ProductDao;
import com.liangjerry.springmall.model.Product;
import com.liangjerry.springmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
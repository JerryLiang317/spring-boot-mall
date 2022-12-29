package com.liangjerry.springmall.dao;

import com.liangjerry.springmall.dto.ProductRequest;
import com.liangjerry.springmall.model.Product;

public interface ProductDao {

    Product getProductById (Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

}

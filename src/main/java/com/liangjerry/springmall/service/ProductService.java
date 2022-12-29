package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.ProductRequest;
import com.liangjerry.springmall.model.Product;

public interface ProductService {

    Product getProductById (Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}

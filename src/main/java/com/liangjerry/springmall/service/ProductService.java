package com.liangjerry.springmall.service;

import com.liangjerry.springmall.constant.ProductCategory;
import com.liangjerry.springmall.dto.ProductRequest;
import com.liangjerry.springmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById (Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}

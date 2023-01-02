package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.ProductQueryParams;
import com.liangjerry.springmall.dto.ProductRequest;
import com.liangjerry.springmall.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById (Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}

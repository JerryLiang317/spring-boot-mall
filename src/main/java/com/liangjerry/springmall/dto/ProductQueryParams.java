package com.liangjerry.springmall.dto;

import com.liangjerry.springmall.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
}

package com.liangjerry.springmall.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductOrder {

    private Integer orderId;
    private Integer userId;
    private Integer totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;
}

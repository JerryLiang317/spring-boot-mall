package com.liangjerry.springmall.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProductOrder {

    private Integer orderId;
    private Integer userId;
    private Integer totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;

    private List<OrderItem> orderItemList;
}

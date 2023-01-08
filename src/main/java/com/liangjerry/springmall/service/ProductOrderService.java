package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.model.ProductOrder;

public interface ProductOrderService {

    ProductOrder getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}

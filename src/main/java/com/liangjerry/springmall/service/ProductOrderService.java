package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.CreateOrderRequest;

public interface ProductOrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}

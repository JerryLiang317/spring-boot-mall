package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.dto.OrderQueryParams;
import com.liangjerry.springmall.model.ProductOrder;

import java.util.List;

public interface ProductOrderService {

    Integer countOrders(OrderQueryParams orderQueryParams);

    List<ProductOrder> getOrders(OrderQueryParams orderQueryParams);

    ProductOrder getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}

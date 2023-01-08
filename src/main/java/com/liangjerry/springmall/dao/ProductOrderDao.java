package com.liangjerry.springmall.dao;

import com.liangjerry.springmall.model.OrderItem;

import java.util.List;

public interface ProductOrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}

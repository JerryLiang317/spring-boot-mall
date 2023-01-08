package com.liangjerry.springmall.dao;

import com.liangjerry.springmall.model.OrderItem;
import com.liangjerry.springmall.model.ProductOrder;

import java.util.List;

public interface ProductOrderDao {

    ProductOrder getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}

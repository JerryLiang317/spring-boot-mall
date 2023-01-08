package com.liangjerry.springmall.dao;

import com.liangjerry.springmall.dto.OrderQueryParams;
import com.liangjerry.springmall.model.OrderItem;
import com.liangjerry.springmall.model.ProductOrder;

import java.util.List;

public interface ProductOrderDao {

    Integer countOrders(OrderQueryParams orderQueryParams);

    List<ProductOrder> getOrders(OrderQueryParams orderQueryParams);

    ProductOrder getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}

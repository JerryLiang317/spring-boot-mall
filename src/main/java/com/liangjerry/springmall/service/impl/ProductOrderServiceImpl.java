package com.liangjerry.springmall.service.impl;

import com.liangjerry.springmall.dao.ProductDao;
import com.liangjerry.springmall.dao.ProductOrderDao;
import com.liangjerry.springmall.dto.BuyItem;
import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.model.OrderItem;
import com.liangjerry.springmall.model.Product;
import com.liangjerry.springmall.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem:createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //計算總價錢
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //創建訂單
        Integer orderId = productOrderDao.createOrder(userId, totalAmount);

        productOrderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}

package com.liangjerry.springmall.service.impl;

import com.liangjerry.springmall.dao.ProductDao;
import com.liangjerry.springmall.dao.ProductOrderDao;
import com.liangjerry.springmall.dao.UserDao;
import com.liangjerry.springmall.dto.BuyItem;
import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.dto.OrderQueryParams;
import com.liangjerry.springmall.model.OrderItem;
import com.liangjerry.springmall.model.Product;
import com.liangjerry.springmall.model.ProductOrder;
import com.liangjerry.springmall.model.User;
import com.liangjerry.springmall.service.ProductOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderDao productOrderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrders(OrderQueryParams orderQueryParams) {
        return productOrderDao.countOrders(orderQueryParams);
    }

    @Override
    public List<ProductOrder> getOrders(OrderQueryParams orderQueryParams) {
        List<ProductOrder> orderList = productOrderDao.getOrders(orderQueryParams);

        for (ProductOrder order: orderList) {
            List<OrderItem> orderItemList = productOrderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public ProductOrder getOrderById(Integer orderId) {
        ProductOrder order = productOrderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = productOrderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);
        if(user == null){
            log.warn("??? userId {} ?????????", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem:createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //?????? product ?????????????????????????????????
            if(product == null){
                log.warn("?????? {} ?????????", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if(product.getStock() < buyItem.getQuantity()) {
                log.warn("?????? {} ??????????????????????????????????????????????????? {}????????????????????? {}", buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //??????????????????
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            //???????????????
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            //?????? BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        //????????????
        Integer orderId = productOrderDao.createOrder(userId, totalAmount);

        productOrderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}

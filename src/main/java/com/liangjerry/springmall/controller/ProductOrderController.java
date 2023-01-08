package com.liangjerry.springmall.controller;

import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.model.ProductOrder;
import com.liangjerry.springmall.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId, @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = productOrderService.createOrder(userId, createOrderRequest);

        ProductOrder order = productOrderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}

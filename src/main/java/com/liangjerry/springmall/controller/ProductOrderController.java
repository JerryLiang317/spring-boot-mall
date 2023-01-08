package com.liangjerry.springmall.controller;

import com.liangjerry.springmall.dto.CreateOrderRequest;
import com.liangjerry.springmall.dto.OrderQueryParams;
import com.liangjerry.springmall.model.ProductOrder;
import com.liangjerry.springmall.service.ProductOrderService;
import com.liangjerry.springmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<ProductOrder>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //取得 order List
        List<ProductOrder> orderList = productOrderService.getOrders(orderQueryParams);

        //取得 order 總數
        Integer count = productOrderService.countOrders(orderQueryParams);

        //分頁
        Page<ProductOrder> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId, @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = productOrderService.createOrder(userId, createOrderRequest);

        ProductOrder order = productOrderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}

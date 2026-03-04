package com.practiceproject.ecommerce_api.controller;

import com.practiceproject.ecommerce_api.dto.ApiResponse;
import com.practiceproject.ecommerce_api.entity.Order;
import com.practiceproject.ecommerce_api.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ApiResponse<String> placeOrder(){
        return new ApiResponse<>(
                "success",
                orderService.placeOrder()
        );
    }

    @GetMapping
    public ApiResponse<List<Order>> getMyOrder(){
        return new ApiResponse<>(
                "success",
                orderService.getMyOrders()
        );
    }
}

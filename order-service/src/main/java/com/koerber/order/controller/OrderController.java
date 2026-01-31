package com.koerber.order.controller;

import com.koerber.order.dto.OrderRequest;
import com.koerber.order.dto.OrderResponse;
import com.koerber.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}

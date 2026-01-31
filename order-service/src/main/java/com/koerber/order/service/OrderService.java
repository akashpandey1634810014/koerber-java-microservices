package com.koerber.order.service;

import com.koerber.order.dto.OrderRequest;
import com.koerber.order.dto.OrderResponse;
import com.koerber.order.entity.OrderEntity;
import com.koerber.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    private static final String INVENTORY_UPDATE_URL =
            "http://localhost:8081/inventory/update?productId={productId}&quantity={quantity}";

    public OrderService(OrderRepository repository,
                        RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public OrderResponse placeOrder(OrderRequest request) {

        // 1. Call Inventory Service
        restTemplate.postForObject(
                INVENTORY_UPDATE_URL,
                null,
                Void.class,
                request.getProductId(),
                request.getQuantity()
        );

        // 2. Save order
        OrderEntity order = OrderEntity.builder()
                .productId(request.getProductId())
                .productName("AUTO_FETCHED") // simplified per assignment
                .quantity(request.getQuantity())
                .status("PLACED")
                .orderDate(LocalDate.now())
                .build();

        OrderEntity saved = repository.save(order);

        // 3. Response
        return OrderResponse.builder()
                .orderId(saved.getOrderId())
                .productId(saved.getProductId())
                .productName(saved.getProductName())
                .quantity(saved.getQuantity())
                .status(saved.getStatus())
                .message("Order placed. Inventory reserved.")
                .build();
    }
}

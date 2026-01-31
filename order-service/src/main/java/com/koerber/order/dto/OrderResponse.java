package com.koerber.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderResponse {

    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private String status;
    private String message;
}

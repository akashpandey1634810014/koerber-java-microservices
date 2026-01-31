package com.koerber.order.service;

import com.koerber.order.dto.OrderRequest;
import com.koerber.order.dto.OrderResponse;
import com.koerber.order.entity.OrderEntity;
import com.koerber.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrderSuccessfully() {
        OrderRequest request = new OrderRequest();
        request.setProductId(1005L);
        request.setQuantity(2);

        OrderEntity savedOrder = new OrderEntity();
        savedOrder.setOrderId(1L);
        savedOrder.setProductId(1005L);
        savedOrder.setQuantity(2);
        savedOrder.setStatus("PLACED");

        when(repository.save(any(OrderEntity.class)))
                .thenReturn(savedOrder);

        OrderResponse response = orderService.placeOrder(request);

        assertNotNull(response);
        assertEquals(1005L, response.getProductId());
        assertEquals("PLACED", response.getStatus());

        verify(restTemplate, times(1))
                .postForObject(anyString(), isNull(), eq(Void.class),
                        eq(1005L), eq(2));

        verify(repository, times(1))
                .save(any(OrderEntity.class));
    }
}

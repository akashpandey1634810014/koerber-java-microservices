package com.koerber.inventory.service;

import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.factory.InventoryFactory;
import com.koerber.inventory.factory.InventoryHandler;
import com.koerber.inventory.repository.InventoryBatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryBatchRepository repository;

    @Mock
    private InventoryFactory factory;

    @Mock
    private InventoryHandler handler;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void shouldReturnInventorySortedByExpiryDate() {
        Long productId = 1005L;

        InventoryBatch batch1 = new InventoryBatch();
        batch1.setBatchId(1L);
        batch1.setExpiryDate(LocalDate.of(2026, 3, 31));

        InventoryBatch batch2 = new InventoryBatch();
        batch2.setBatchId(2L);
        batch2.setExpiryDate(LocalDate.of(2026, 5, 30));

        when(repository.findByProductIdOrderByExpiryDateAsc(productId))
                .thenReturn(Arrays.asList(batch1, batch2));

        List<InventoryBatch> result = inventoryService.getInventory(productId);

        assertEquals(2, result.size());
        assertEquals(batch1.getBatchId(), result.get(0).getBatchId());
        verify(repository, times(1))
                .findByProductIdOrderByExpiryDateAsc(productId);
    }

    @Test
    void shouldUpdateInventoryUsingFactoryHandler() {
        Long productId = 1001L;
        Integer quantity = 5;

        when(factory.getHandler()).thenReturn(handler);

        inventoryService.updateInventory(productId, quantity);

        verify(handler, times(1))
                .updateInventory(productId, quantity);
    }
}

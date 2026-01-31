package com.koerber.inventory.factory;

import com.koerber.inventory.entity.InventoryBatch;

import com.koerber.inventory.repository.InventoryBatchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultInventoryHandler implements InventoryHandler {

    private final InventoryBatchRepository repository;

    public DefaultInventoryHandler(InventoryBatchRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateInventory(Long productId, Integer quantity) {

        List<InventoryBatch> batches =
                repository.findByProductIdOrderByExpiryDateAsc(productId);

        int remaining = quantity;

        for (InventoryBatch batch : batches) {
            if (remaining <= 0) break;

            int deduct = Math.min(batch.getQuantity(), remaining);
            batch.setQuantity(batch.getQuantity() - deduct);
            remaining -= deduct;

            repository.save(batch);
        }

        if (remaining > 0) {
            throw new RuntimeException("Insufficient inventory");
        }
    }
}

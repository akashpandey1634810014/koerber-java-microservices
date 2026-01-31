package com.koerber.inventory.service;

import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.factory.InventoryFactory;
import com.koerber.inventory.repository.InventoryBatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryBatchRepository repository;
    private final InventoryFactory factory;

    public InventoryService(InventoryBatchRepository repository,
                            InventoryFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public List<InventoryBatch> getInventory(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }

    public void updateInventory(Long productId, Integer quantity) {
        factory.getHandler().updateInventory(productId, quantity);
    }
}

package com.koerber.inventory.factory;

public interface InventoryHandler {

    void updateInventory(Long productId, Integer quantity);
}

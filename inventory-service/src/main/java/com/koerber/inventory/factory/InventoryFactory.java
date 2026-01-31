package com.koerber.inventory.factory;

import org.springframework.stereotype.Component;

@Component
public class InventoryFactory {

    private final DefaultInventoryHandler defaultHandler;

    public InventoryFactory(DefaultInventoryHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public InventoryHandler getHandler() {
        return defaultHandler;
    }
}

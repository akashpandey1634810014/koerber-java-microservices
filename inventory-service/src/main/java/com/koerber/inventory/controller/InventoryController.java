package com.koerber.inventory.controller;

import com.koerber.inventory.entity.InventoryBatch;
import com.koerber.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public List<InventoryBatch> getInventory(@PathVariable Long productId) {
        return service.getInventory(productId);
    }

    @PostMapping("/update")
    public void updateInventory(@RequestParam Long productId,
                                @RequestParam Integer quantity) {
        service.updateInventory(productId, quantity);
    }
}

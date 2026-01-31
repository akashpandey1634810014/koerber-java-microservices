package com.koerber.inventory.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inventory_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    private Long productId;
    private String productName;
    private Integer quantity;
    private LocalDate expiryDate;
}

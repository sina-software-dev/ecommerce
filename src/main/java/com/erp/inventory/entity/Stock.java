package com.erp.inventory.entity;

import com.erp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock extends BaseEntity {
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "quantity_on_hand", nullable = false)
    private Integer quantityOnHand = 0;
    
    @Column(name = "quantity_reserved", nullable = false)
    private Integer quantityReserved = 0;
    
    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable = 0;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @Column(name = "last_stock_count_date")
    private LocalDateTime lastStockCountDate;
    
    @Column(name = "last_movement_date")
    private LocalDateTime lastMovementDate;
    
    @PrePersist
    @PreUpdate
    private void calculateAvailableQuantity() {
        this.quantityAvailable = this.quantityOnHand - this.quantityReserved;
    }
}
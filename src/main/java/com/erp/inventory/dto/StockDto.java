package com.erp.inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    
    private Long id;
    
    @NotNull(message = "Product ID is required")
    private Long productId;
    private String productName;
    private String productSku;
    
    @Min(value = 0, message = "Quantity on hand cannot be negative")
    private Integer quantityOnHand;
    
    @Min(value = 0, message = "Quantity reserved cannot be negative")
    private Integer quantityReserved;
    
    private Integer quantityAvailable; // This is calculated field
    
    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;
    
    private LocalDateTime lastStockCountDate;
    private LocalDateTime lastMovementDate;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
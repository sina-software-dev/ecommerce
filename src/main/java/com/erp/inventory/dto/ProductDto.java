package com.erp.inventory.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    
    private Long id;
    
    @NotBlank(message = "Product name is required")
    @Size(max = 200, message = "Product name must not exceed 200 characters")
    private String name;
    
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;
    
    @NotBlank(message = "SKU is required")
    @Size(max = 50, message = "SKU must not exceed 50 characters")
    private String sku;
    
    @Size(max = 100, message = "Barcode must not exceed 100 characters")
    private String barcode;
    
    @NotNull(message = "Category is required")
    private Long categoryId;
    private String categoryName;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit price must be greater than 0")
    private BigDecimal unitPrice;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost price must be greater than 0")
    private BigDecimal costPrice;
    
    @Size(max = 20, message = "Unit of measure must not exceed 20 characters")
    private String unitOfMeasure;
    
    private BigDecimal weight;
    
    @Size(max = 100, message = "Dimensions must not exceed 100 characters")
    private String dimensions;
    
    private Integer reorderLevel;
    private Integer maxStockLevel;
    private Integer minStockLevel;
    private LocalDate expiryDate;
    private LocalDate manufacturingDate;
    
    @Size(max = 50, message = "Batch number must not exceed 50 characters")
    private String batchNumber;
    
    private BigDecimal taxRate;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Stock information
    private StockDto stock;
}
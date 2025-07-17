package com.sina.ecommerce.controller;

import com.sina.ecommerce.model.Supplier;
import com.sina.ecommerce.service.SupplierService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@RestController
@RequestMapping("/suppliers")
@Validated
public class SupplierController {

    private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id) {
        logger.info("Fetching supplier with id: {}", id);
        Supplier supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    public ResponseEntity<Supplier> postSupplier(@Valid @RequestBody Supplier supplier) {
        logger.info("Creating new supplier");
        Supplier createdSupplier = supplierService.createSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @PutMapping("{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @Valid @RequestBody Supplier supplier) {
        logger.info("Updating supplier with id: {}", id);
        supplier.setId(id);
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        logger.info("Deleting supplier with id: {}", id);
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
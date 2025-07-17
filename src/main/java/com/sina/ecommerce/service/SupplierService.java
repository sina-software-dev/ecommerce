package com.sina.ecommerce.service;

import com.sina.ecommerce.model.Supplier;
import com.sina.ecommerce.repository.SupplierRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
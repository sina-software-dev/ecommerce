package com.sina.ecommerce.controller;

import com.sina.ecommerce.model.Address;
import com.sina.ecommerce.service.AddressService;
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
@RequestMapping("/addresses")
@Validated
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        logger.info("Fetching address with id: {}", id);
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Address> postAddress(@Valid @RequestBody Address address) {
        logger.info("Creating new address");
        Address createdAddress = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody Address address) {
        logger.info("Updating address with id: {}", id);
        address.setId(id);
        Address updatedAddress = addressService.updateAddress(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        logger.info("Deleting address with id: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

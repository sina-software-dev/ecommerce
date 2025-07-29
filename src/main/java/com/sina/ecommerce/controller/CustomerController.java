package com.sina.ecommerce.controller;

import com.sina.ecommerce.dto.*;
import com.sina.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/customers")
@Slf4j
@Validated
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer", description = "Creates a new customer with username")
    public ResponseEntity<GeneralResponse<CreateCustomerResponseDto>> postCustomer(@Valid @RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
        log.info("Creating new customer", StructuredArguments.keyValue("username", createCustomerRequestDto.getUsername()));
        CreateCustomerResponseDto savedCustomer = customerService.createCustomer(createCustomerRequestDto);
        log.info("Customer created with", StructuredArguments.keyValue("id", savedCustomer.getId()));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(new GeneralResponse<>(true,savedCustomer));
    }

    /*@GetMapping("{id}")
    public ResponseEntity<GeneralResponse<GetCustomerByIdDto>> getCustomer(@PathVariable Long id) {
        log.info("Fetching customer with id: {}", id);
        return ResponseEntity.ok(
                new GeneralResponse<>(true, null,customerService.getCustomerById(id),null));
    }*/

    /*@PutMapping("{id}")
    public ResponseEntity<GeneralResponse<UpdateCustomerResponseDto>> updateCustomer(@PathVariable Long id, @Valid @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        log.info("Updating customer with id: {}", id);
        updateCustomerRequestDto.setId(id);
        return ResponseEntity.ok(new GeneralResponse<>(true, customerService.updateCustomer(updateCustomerRequestDto)));
    }*/

    /*

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.info("Deleting customer with id: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }*/
}


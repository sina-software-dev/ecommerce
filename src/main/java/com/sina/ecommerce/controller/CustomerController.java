package com.sina.ecommerce.controller;

import com.sina.ecommerce.dto.CreateCustomerRequestDto;
import com.sina.ecommerce.dto.CreateCustomerResponseDto;
import com.sina.ecommerce.dto.GeneralResponse;
import com.sina.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCustomer.getId())
                .toUri();
        return ResponseEntity.created(location).body(new GeneralResponse<>(true, savedCustomer));
    }
}


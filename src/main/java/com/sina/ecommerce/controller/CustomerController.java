package com.sina.ecommerce.controller;

import com.sina.ecommerce.dto.CreateCustomerRequestDto;
import com.sina.ecommerce.dto.CreateCustomerResponseDto;
import com.sina.ecommerce.dto.GeneralResponse;
import com.sina.ecommerce.dto.GetCustomerByIdDto;
import com.sina.ecommerce.model.Customer;
import com.sina.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Slf4j
@Validated
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Create a new customer", description = "Creates a new customer with username")
    @PostMapping
    public ResponseEntity<GeneralResponse<CreateCustomerResponseDto>> postCustomer(
            @Valid @RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
        log.info("Creating new customer");
        return ResponseEntity.status(HttpStatus.CREATED).body(new GeneralResponse<>(
                true,
                "",
                customerService.createCustomer(
                        createCustomerRequestDto
                ),
                null));
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<GetCustomerByIdDto>> getCustomer(@PathVariable Long id) {
        log.info("Fetching customer with id: {}", id);
        return ResponseEntity.ok(
                new GeneralResponse<>(true, null,customerService.getCustomerById(id),null));
    }

    /*@PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        logger.info("Updating customer with id: {}", id);
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        logger.info("Deleting customer with id: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }*/
}


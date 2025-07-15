package com.sina.ecommerce.controller;

import com.sina.ecommerce.model.Customer;
import com.sina.ecommerce.service.CustomerService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer postCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
         customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public void putCustomer(@PathVariable Customer customer) {
        customerService.updateCustomer(customer);
    }
}

package com.sina.ecommerce.service;

import com.sina.ecommerce.dto.CreateCustomerRequestDto;
import com.sina.ecommerce.dto.CreateCustomerResponseDto;
import com.sina.ecommerce.mapper.CustomerMapper;
import com.sina.ecommerce.model.Customer;
import com.sina.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
        if (customerRepository.findByUsername(createCustomerRequestDto.getUsername()).isPresent()) {
            throw new RuntimeException("The Username is already in use");
        }
        return customerMapper.toCreateCustomerDto(
                customerRepository.save(
                        customerMapper.toEntity(
                                createCustomerRequestDto
                        )
                )
        );
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        Customer updatedCustomer;
        if(customerOptional.isPresent())
            updatedCustomer = customerRepository.save(customer);
        else
            throw new RuntimeException("User not found");
        return updatedCustomer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

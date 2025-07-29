package com.sina.ecommerce.service;

import com.sina.ecommerce.dto.*;
import com.sina.ecommerce.exception.custome.CustomerAlreadyExistsException;
import com.sina.ecommerce.mapper.CustomerMapper;
import com.sina.ecommerce.model.Customer;
import com.sina.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Sina Ramezani, 7/16/2025
 */
@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
        Customer entity = customerMapper.toEntity(createCustomerRequestDto);
        Customer savedCustomer = customerRepository.save(entity);
        return customerMapper.toCreateCustomerDto(savedCustomer);
    }

    public GetCustomerByIdDto getCustomerById(Long id) {
        return customerMapper.toGetCustomerByIdDto(
                customerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    /*@Transactional
    public UpdateCustomerResponseDto updateCustomer(UpdateCustomerRequestDto updateCustomerRequestDto) {
        if (customerRepository.findById(updateCustomerRequestDto.getId()).isPresent()) {
            return customerMapper.toUpdateCustomerResponseDto(
                    customerRepository.save(
                            customerMapper.toEntity(
                                    updateCustomerRequestDto
                            )
                    )
            );
        } else {
            throw new RuntimeException("Customer not found");
        }
    }*/
}

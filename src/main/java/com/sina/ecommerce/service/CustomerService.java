package com.sina.ecommerce.service;

import com.sina.ecommerce.dto.CreateCustomerRequestDto;
import com.sina.ecommerce.dto.CreateCustomerResponseDto;
import com.sina.ecommerce.mapper.CustomerMapper;
import com.sina.ecommerce.model.Customer;
import com.sina.ecommerce.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.argument.StructuredArguments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        log.info("Customer created with", StructuredArguments.keyValue("id", savedCustomer.getId()));
        return customerMapper.toCreateCustomerDto(savedCustomer);
    }

    /*public PaginatedResponse<Customer> getCustomer(Pageable pageable) {
        return PaginationUtils.buildResponse(customerRepository.findAll(pageable));
    }*/

    public Page<Customer> getCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}

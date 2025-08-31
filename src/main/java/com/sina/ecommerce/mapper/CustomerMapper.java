package com.sina.ecommerce.mapper;

import com.sina.ecommerce.dto.*;
import com.sina.ecommerce.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

/**
 * @author Sina Ramezani, 7/19/2025
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CreateCustomerRequestDto createCustomerRequestDto);

    CreateCustomerResponseDto toCreateCustomerDto(Customer customer);

    GetCustomerByIdDto toGetCustomerByIdDto(Customer customer);

    Customer toEntity(UpdateCustomerRequestDto updateCustomerRequestDto);

    UpdateCustomerResponseDto toUpdateCustomerResponseDto(Customer customer);

    PaginatedResponse<Customer> toPaginateResponse(Page<Customer> pageableCustomers);
}

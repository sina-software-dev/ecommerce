package com.sina.ecommerce.mapper;

import com.sina.ecommerce.dto.CreateCustomerRequestDto;
import com.sina.ecommerce.dto.CreateCustomerResponseDto;
import com.sina.ecommerce.dto.GetCustomerByIdDto;
import com.sina.ecommerce.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Sina Ramezani, 7/19/2025
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    @Mapping(source = "email", target = "email")
    Customer toEntity(CreateCustomerRequestDto createCustomerRequestDto);
    CreateCustomerResponseDto toCreateCustomerDto(Customer customer);
    GetCustomerByIdDto toGetCustomerByIdDto(Customer customer);
}

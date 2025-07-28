package com.sina.ecommerce.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Sina Ramezani, 7/21/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerResponseDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private String phoneNumber;
    private List<CreateAddressResponseDto> addresses;
}

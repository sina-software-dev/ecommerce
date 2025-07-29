package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sina Ramezani, 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerResponseDto {
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
}

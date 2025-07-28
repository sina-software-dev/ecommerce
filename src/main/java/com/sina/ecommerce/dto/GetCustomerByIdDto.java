package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sina Ramezani, 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByIdDto {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<GetAddressDto> addresses;
}

package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sina Ramezani, 7/21/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressResponseDto {
    private Long id;
    private String country;
    private String city;
    private String street;
    private String valley;
    private String houseNumberPlate;
    private String postalCode;
    private String wholeAddress;
}

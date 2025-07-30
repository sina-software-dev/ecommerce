package com.sina.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sina Ramezani, 7/19/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequestDto {
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    private String valley;
    @NotBlank
    private String houseNumberPlate;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String wholeAddress;
}

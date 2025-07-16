package com.sina.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractEntity{
    private String country;
    private String city;
    private String street;
    private String valley;
    private String houseNumberPlate;
    private String postalCode;
    private String wholeAddress;
    @OneToOne(mappedBy = "address")
    private Order order;
}
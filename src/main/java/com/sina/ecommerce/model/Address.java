package com.sina.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractEntity{
}
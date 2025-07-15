package com.sina.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ORDERS")
public class Order extends AbstractEntity {
}

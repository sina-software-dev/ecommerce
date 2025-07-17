package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sina Ramezani, 7/17/2025
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}

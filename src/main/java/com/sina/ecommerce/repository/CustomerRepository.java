package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

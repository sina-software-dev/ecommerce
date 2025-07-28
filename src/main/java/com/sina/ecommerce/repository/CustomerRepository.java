package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Customer> findByUsername(String username);
}

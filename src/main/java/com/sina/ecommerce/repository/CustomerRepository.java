package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Customer> findByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Customer> findById(@NonNull Long id);
}

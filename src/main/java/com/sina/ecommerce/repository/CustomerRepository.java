package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Customer;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "SELECT * FROM CUSTOMER WHERE username = ?", nativeQuery = true)
    Optional<Customer> findByUsernameWithLock(String username);

    @Query(value = "Select * from customer where id = ?", nativeQuery = true)
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Customer> findByIdWithLock(@NonNull Long id);
}

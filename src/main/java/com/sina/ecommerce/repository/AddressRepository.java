package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sina Ramezani, 7/17/2025
 */
public interface AddressRepository extends JpaRepository<Address, Long> {
}

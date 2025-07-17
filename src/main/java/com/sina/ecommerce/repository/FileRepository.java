package com.sina.ecommerce.repository;

import com.sina.ecommerce.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Sina Ramezani, 7/17/2025
 */
public interface FileRepository extends JpaRepository<File, Long>{
}

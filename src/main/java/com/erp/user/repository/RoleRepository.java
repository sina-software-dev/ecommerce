package com.erp.user.repository;

import com.erp.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(String name);
    
    boolean existsByName(String name);
    
    @Query("SELECT r FROM Role r WHERE r.isActive = true")
    List<Role> findAllActiveRoles();
    
    @Query("SELECT r FROM Role r WHERE r.name IN :names AND r.isActive = true")
    List<Role> findByNameIn(List<String> names);
}
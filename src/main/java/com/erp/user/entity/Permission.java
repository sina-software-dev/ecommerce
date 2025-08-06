package com.erp.user.entity;

import com.erp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {
    
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "description", length = 255)
    private String description;
    
    @Column(name = "resource", nullable = false, length = 100)
    private String resource;
    
    @Column(name = "action", nullable = false, length = 50)
    private String action;
    
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}
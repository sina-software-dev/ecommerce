package com.sina.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Sina Ramezani, 7/16/2025
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS")
@ToString(exclude = "password")
public abstract class User extends AbstractEntity {
    private String username;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
}



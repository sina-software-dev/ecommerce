package com.sina.ecommerce.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class File extends AbstractEntity {
    private String name;
    private String path;
    private String type;
    private String size;
}

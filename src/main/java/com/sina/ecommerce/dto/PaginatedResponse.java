package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Sina Ramezani, 7/31/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse <T>{
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
}

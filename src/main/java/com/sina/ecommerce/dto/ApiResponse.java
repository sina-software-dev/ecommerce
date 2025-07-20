package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sina Ramezani, 7/21/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private List<String> errors;
}

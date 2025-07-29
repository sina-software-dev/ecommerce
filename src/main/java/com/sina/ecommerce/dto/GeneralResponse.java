package com.sina.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Sina Ramezani, 7/21/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GeneralResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public GeneralResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

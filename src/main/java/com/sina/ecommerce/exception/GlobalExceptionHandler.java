package com.sina.ecommerce.exception;

import com.sina.ecommerce.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Sina Ramezani, 7/19/2025
 */
@RestControllerAdvice(basePackages = "com.sina.ecommerce.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse<?>> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(false, ex.getMessage(), null, null));
    }
}
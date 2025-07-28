package com.sina.ecommerce.exception;

import com.sina.ecommerce.dto.GeneralResponse;
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
    public final ResponseEntity<GeneralResponse<?>> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new GeneralResponse<>(false, ex.getMessage(), null, null));
    }
}
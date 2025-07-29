package com.sina.ecommerce.exception;

import com.sina.ecommerce.dto.GeneralResponse;
import com.sina.ecommerce.exception.custome.CustomerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Sina Ramezani, 7/19/2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public final ResponseEntity<GeneralResponse<?>> handleAllExceptions(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new GeneralResponse<>(false, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse<Map<String, Object>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<Map<String, String>> errors = fieldErrors.stream().map(error -> {
            assert error.getDefaultMessage() != null;
            return Map.of("field", error.getField(), "message", error.getDefaultMessage());
        }).collect(Collectors.toList());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("errors", errors);
        return ResponseEntity.badRequest().body(new GeneralResponse<Map<String, Object>>(false, "Validation failed", body));
    }
}
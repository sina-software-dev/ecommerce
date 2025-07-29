package com.sina.ecommerce.validation;

import com.sina.ecommerce.repository.CustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Sina Ramezani, 7/29/2025
 */
@Component
@RequiredArgsConstructor
public class DoesNotExistsValidator implements ConstraintValidator<CustomerNotExists, Long> {
    private final CustomerRepository customerRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return customerRepository.findById(id).isEmpty();
    }
}

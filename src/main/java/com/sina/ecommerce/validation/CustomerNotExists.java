package com.sina.ecommerce.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author Sina Ramezani, 7/29/2025
 */
@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerNotExists {
    String message() default "Customer Not Exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

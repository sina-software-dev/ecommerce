package com.sina.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sina Ramezani, 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequestDto {
    private Long id;
    @NotBlank
    @Pattern(
            regexp = "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z][a-zA-Z0-9._]+(?<![_.])$",
            message = "Username must be 3–20 characters, start with a letter, and contain only letters, digits," +
                    " underscores, or dots. No special characters at the beginning or end."
    )
    private String username;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 1, max = 50, message = "Name must be 1–50 characters")
    private String name;
    @Size(min = 1, max = 50, message = "Last name must be 1–50 characters")
    private String lastName;
    @Size(max = 20, message = "Phone number can't exceed 20 characters")
    private String phoneNumber;
}

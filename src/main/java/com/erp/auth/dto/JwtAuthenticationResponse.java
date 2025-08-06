package com.erp.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    
    private String accessToken;
    private String tokenType = "Bearer";
    private Long expiresIn;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    
    public JwtAuthenticationResponse(String accessToken, Long expiresIn, String username, String email, String firstName, String lastName) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
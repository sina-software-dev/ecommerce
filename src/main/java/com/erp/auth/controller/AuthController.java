package com.erp.auth.controller;

import com.erp.auth.dto.JwtAuthenticationResponse;
import com.erp.auth.dto.LoginRequest;
import com.erp.security.jwt.JwtTokenProvider;
import com.erp.security.service.UserPrincipal;
import com.erp.user.dto.CreateUserDto;
import com.erp.user.dto.UserDto;
import com.erp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    
    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;
    
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user and returns JWT token")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(
            jwt,
            (long) jwtExpirationInMs,
            userPrincipal.getUsername(),
            userPrincipal.getEmail(),
            userPrincipal.getFirstName(),
            userPrincipal.getLastName()
        ));
    }
    
    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Registers a new user in the system")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserDto user = userService.createUser(createUserDto);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Returns current authenticated user details")
    public ResponseEntity<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        UserDto user = userService.getUserByUsername(userPrincipal.getUsername());
        return ResponseEntity.ok(user);
    }
}
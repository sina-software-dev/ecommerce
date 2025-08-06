package com.erp.user.controller;

import com.erp.user.dto.CreateUserDto;
import com.erp.user.dto.UserDto;
import com.erp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users in the ERP system")
public class UserController {
    
    private final UserService userService;
    
    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user in the system")
    @PreAuthorize("hasPermission('user', 'create')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserDto createdUser = userService.createUser(createUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Retrieves a user by their username")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<UserDto> getUserByUsername(
            @Parameter(description = "Username") @PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieves a user by their email")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<UserDto> getUserByEmail(
            @Parameter(description = "Email address") @PathVariable String email) {
        UserDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all active users with pagination")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search users", description = "Searches users by username, email, first name, or last name")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<Page<UserDto>> searchUsers(
            @Parameter(description = "Search term") @RequestParam String search,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> users = userService.searchUsers(search, pageable);
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/department/{department}")
    @Operation(summary = "Get users by department", description = "Retrieves users by their department")
    @PreAuthorize("hasPermission('user', 'read')")
    public ResponseEntity<Page<UserDto>> getUsersByDepartment(
            @Parameter(description = "Department name") @PathVariable String department,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> users = userService.getUsersByDepartment(department, pageable);
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user")
    @PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Permanently deletes a user")
    @PreAuthorize("hasPermission('user', 'delete')")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate user", description = "Deactivates a user account")
    @PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<Void> deactivateUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
    
    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate user", description = "Activates a user account")
    @PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<Void> activateUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/roles")
    @Operation(summary = "Assign roles to user", description = "Assigns roles to a user")
    @PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<UserDto> assignRolesToUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestBody List<Long> roleIds) {
        UserDto updatedUser = userService.assignRolesToUser(id, roleIds);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}/roles")
    @Operation(summary = "Remove roles from user", description = "Removes roles from a user")
    @PreAuthorize("hasPermission('user', 'update')")
    public ResponseEntity<UserDto> removeRolesFromUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestBody List<Long> roleIds) {
        UserDto updatedUser = userService.removeRolesFromUser(id, roleIds);
        return ResponseEntity.ok(updatedUser);
    }
    
    @GetMapping("/check-username/{username}")
    @Operation(summary = "Check username availability", description = "Checks if a username is available")
    public ResponseEntity<Boolean> checkUsernameAvailability(
            @Parameter(description = "Username to check") @PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(!exists);
    }
    
    @GetMapping("/check-email/{email}")
    @Operation(summary = "Check email availability", description = "Checks if an email is available")
    public ResponseEntity<Boolean> checkEmailAvailability(
            @Parameter(description = "Email to check") @PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(!exists);
    }
}
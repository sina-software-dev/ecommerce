package com.erp.user.service;

import com.erp.user.dto.CreateUserDto;
import com.erp.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    
    UserDto createUser(CreateUserDto createUserDto);
    
    UserDto updateUser(Long id, UserDto userDto);
    
    UserDto getUserById(Long id);
    
    UserDto getUserByUsername(String username);
    
    UserDto getUserByEmail(String email);
    
    Page<UserDto> getAllUsers(Pageable pageable);
    
    Page<UserDto> searchUsers(String search, Pageable pageable);
    
    Page<UserDto> getUsersByDepartment(String department, Pageable pageable);
    
    void deleteUser(Long id);
    
    void deactivateUser(Long id);
    
    void activateUser(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    UserDto assignRolesToUser(Long userId, List<Long> roleIds);
    
    UserDto removeRolesFromUser(Long userId, List<Long> roleIds);
}
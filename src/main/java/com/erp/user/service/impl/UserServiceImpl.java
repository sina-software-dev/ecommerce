package com.erp.user.service.impl;

import com.erp.user.dto.CreateUserDto;
import com.erp.user.dto.UserDto;
import com.erp.user.entity.Role;
import com.erp.user.entity.User;
import com.erp.user.mapper.UserMapper;
import com.erp.user.repository.RoleRepository;
import com.erp.user.repository.UserRepository;
import com.erp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        log.info("Creating new user with username: {}", createUserDto.getUsername());
        
        if (existsByUsername(createUserDto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + createUserDto.getUsername());
        }
        
        if (existsByEmail(createUserDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + createUserDto.getEmail());
        }
        
        User user = userMapper.toEntity(createUserDto);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        
        // Assign roles if provided
        if (createUserDto.getRoleIds() != null && !createUserDto.getRoleIds().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (Long roleId : createUserDto.getRoleIds()) {
                Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + roleId));
                roles.add(role);
            }
            user.setRoles(roles);
        }
        
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());
        
        return userMapper.toDto(savedUser);
    }
    
    @Override
    @CachePut(value = "users", key = "#result.id")
    public UserDto updateUser(Long id, UserDto userDto) {
        log.info("Updating user with id: {}", id);
        
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        userMapper.updateEntityFromDto(userDto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        
        log.info("User updated successfully with id: {}", updatedUser.getId());
        return userMapper.toDto(updatedUser);
    }
    
    @Override
    @Cacheable(value = "users", key = "#id")
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        log.debug("Fetching user by id: {}", id);
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByUsername(String username) {
        log.debug("Fetching user by username: {}", username);
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
        
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {
        log.debug("Fetching user by email: {}", email);
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        
        return userMapper.toDto(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Pageable pageable) {
        log.debug("Fetching all active users with pagination");
        
        Page<User> users = userRepository.findAllActiveUsers(pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> searchUsers(String search, Pageable pageable) {
        log.debug("Searching users with term: {}", search);
        
        Page<User> users = userRepository.searchActiveUsers(search, pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getUsersByDepartment(String department, Pageable pageable) {
        log.debug("Fetching users by department: {}", department);
        
        Page<User> users = userRepository.findByDepartment(department, pageable);
        return users.map(userMapper::toDto);
    }
    
    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        
        userRepository.deleteById(id);
        log.info("User deleted successfully with id: {}", id);
    }
    
    @Override
    @CachePut(value = "users", key = "#id")
    public void deactivateUser(Long id) {
        log.info("Deactivating user with id: {}", id);
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        user.setIsActive(false);
        user.setIsEnabled(false);
        userRepository.save(user);
        
        log.info("User deactivated successfully with id: {}", id);
    }
    
    @Override
    @CachePut(value = "users", key = "#id")
    public void activateUser(Long id) {
        log.info("Activating user with id: {}", id);
        
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        user.setIsActive(true);
        user.setIsEnabled(true);
        userRepository.save(user);
        
        log.info("User activated successfully with id: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    @CachePut(value = "users", key = "#userId")
    public UserDto assignRolesToUser(Long userId, List<Long> roleIds) {
        log.info("Assigning roles to user with id: {}", userId);
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        Set<Role> newRoles = new HashSet<>();
        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with id: " + roleId));
            newRoles.add(role);
        }
        
        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }
        user.getRoles().addAll(newRoles);
        
        User updatedUser = userRepository.save(user);
        log.info("Roles assigned successfully to user with id: {}", userId);
        
        return userMapper.toDto(updatedUser);
    }
    
    @Override
    @CachePut(value = "users", key = "#userId")
    public UserDto removeRolesFromUser(Long userId, List<Long> roleIds) {
        log.info("Removing roles from user with id: {}", userId);
        
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        if (user.getRoles() != null) {
            user.getRoles().removeIf(role -> roleIds.contains(role.getId()));
        }
        
        User updatedUser = userRepository.save(user);
        log.info("Roles removed successfully from user with id: {}", userId);
        
        return userMapper.toDto(updatedUser);
    }
}
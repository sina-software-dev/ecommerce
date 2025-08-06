package com.erp.user.mapper;

import com.erp.user.dto.PermissionDto;
import com.erp.user.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissionMapper {
    
    PermissionDto toDto(Permission permission);
    
    List<PermissionDto> toDtoList(List<Permission> permissions);
    
    Set<PermissionDto> toDtoSet(Set<Permission> permissions);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "roles", ignore = true)
    Permission toEntity(PermissionDto permissionDto);
}
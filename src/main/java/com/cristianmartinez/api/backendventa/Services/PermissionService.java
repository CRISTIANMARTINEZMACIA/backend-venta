package com.cristianmartinez.api.backendventa.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.cristianmartinez.api.backendventa.Entity.Permission;
import com.cristianmartinez.api.backendventa.Repository.PermissionRepository;
import com.cristianmartinez.api.backendventa.dto.request.PermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PermissionResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository repository;

    public PaginationResponse<List<PermissionResponse>> findAll(Pageable pageable) {
       
       Page<Permission> permission = repository.findAll(pageable);
        return PaginationResponse.<List<PermissionResponse>>builder()
         .count(permission.getTotalElements())
         .page(permission.getTotalPages())
         .content(permission.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList()))
         .build();
    }

    public PermissionResponse findById(Long id) {
         return mapToResponse(repository.findById(id).get());
    }

    public PermissionResponse save(PermissionRequest permissionRequest) {
        return mapToResponse(repository.save(mapToEntity(permissionRequest)));
    }

    public PermissionResponse update(PermissionRequest permissionRequest, Long id) {
           
        Permission entity = repository.findById(id).get();

        entity.setName(permissionRequest.getName());
        entity.setEndpointBase(permissionRequest.getEndpointBase());
        entity.setDescription(permissionRequest.getDescription());

        Permission saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Permission mapToEntity(PermissionRequest permissionRequest) {
        return Permission.builder()
                .description(permissionRequest.getDescription())
                .name(permissionRequest.getName())
                .endpointBase(permissionRequest.getEndpointBase())
                .build();
    }

    

    private PermissionResponse mapToResponse(Permission permission) {
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .endpointBase(permission.getEndpointBase())
                .build();
    }
}

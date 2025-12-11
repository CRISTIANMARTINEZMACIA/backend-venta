package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Repository.RolRepository;
import com.cristianmartinez.api.backendventa.dto.request.RolRequest;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolService {
    @Autowired
    private RolRepository repository;

   public PaginationResponse<List<RolResponse>> findAll(Pageable pageable) {

    Page<Rol> roles = repository.findAll(pageable);

       return PaginationResponse.<List<RolResponse>>builder()
         .count(roles.getTotalElements())
         .page(roles.getTotalPages())
         .content(roles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList()))
         .build();
    }

    public RolResponse findById(Long id) {
         return mapToResponse(repository.findById(id).get());
    }

    public RolResponse save(RolRequest rolRequest) {
        return mapToResponse(repository.save(mapToEntity(rolRequest)));
    }

    public RolResponse update(RolRequest rolRequest, Long id) {
           
        Rol entity = repository.findById(id).get();

        entity.setName(rolRequest.getName());
        entity.setDescription(rolRequest.getDescription());

        Rol saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Rol mapToEntity(RolRequest rolRequest) {
        return Rol.builder()
                .description(rolRequest.getDescription())
                .name(rolRequest.getName())
                .build();
    }

    private RolResponse mapToResponse(Rol rol) {
        return RolResponse.builder()
                .id(rol.getId())
                .name(rol.getName())
                .description(rol.getDescription())
                .build();
    }
}
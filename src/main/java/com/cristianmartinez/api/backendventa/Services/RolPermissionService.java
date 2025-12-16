package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Permission;
import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Entity.RolPermission;
import com.cristianmartinez.api.backendventa.Entity.ViewPermission;
import com.cristianmartinez.api.backendventa.Entity.Views;
import com.cristianmartinez.api.backendventa.Repository.RolPermissionRepository;
import com.cristianmartinez.api.backendventa.dto.request.RolPermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PermissionResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolPermissionResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewPermissionResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolPermissionService {
    @Autowired
    private RolPermissionRepository repository;

    public PaginationResponse<List<RolPermissionResponse>> findAll(Pageable pageable) {
        Page<RolPermission> business = repository.findAll(pageable);
        return PaginationResponse.<List<RolPermissionResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public RolPermissionResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public RolPermissionResponse save(RolPermissionRequest rolPermissionRequest) {
        return mapToResponse(repository.save(mapToEntity(rolPermissionRequest)));
    }

    public RolPermissionResponse update(RolPermissionRequest rolPermissionRequest, Long id) {

        RolPermission entity = repository.findById(id).get();

        entity.setRol(Rol.builder().id(rolPermissionRequest.getRol()).build());
        entity.setViewPermission(ViewPermission.builder().id(rolPermissionRequest.getViewPermission()).build());
        entity.setUpdate(rolPermissionRequest.getUpdate());
        entity.setDelete(rolPermissionRequest.getDelete());
        entity.setLead(rolPermissionRequest.getLead());
        entity.setPrint(rolPermissionRequest.getPrint());
        entity.setRead(rolPermissionRequest.getRead());
        entity.setWrite(rolPermissionRequest.getWrite());

        RolPermission saved = repository.save(entity);
        return mapToResponse(saved);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private RolPermission mapToEntity(RolPermissionRequest rolPermissionRequest) {
        return RolPermission.builder()
                .rol(Rol.builder().id(rolPermissionRequest.getRol()).build())
                .viewPermission(ViewPermission.builder().id(rolPermissionRequest.getViewPermission()).build())
                .update(rolPermissionRequest.getUpdate())
                .delete(rolPermissionRequest.getDelete())
                .lead(rolPermissionRequest.getLead())
                .print(rolPermissionRequest.getPrint())
                .read(rolPermissionRequest.getRead())
                .write(rolPermissionRequest.getWrite())
                .build();
    }

    private RolPermissionResponse mapToResponse(RolPermission rolPermission) {
        return RolPermissionResponse.builder()
                .id(rolPermission.getId())
                .rol(mapToResponseRole(rolPermission.getRol()))
                .viewPermission(mapToResponseViewPermission(rolPermission.getViewPermission()))
                .update(rolPermission.getUpdate())
                .delete(rolPermission.getDelete())
                .lead(rolPermission.getLead())
                .print(rolPermission.getPrint())
                .read(rolPermission.getRead())
                .write(rolPermission.getWrite())
                .build();
    }

    private ViewPermissionResponse mapToResponseViewPermission(ViewPermission viewPermission) {
        return ViewPermissionResponse.builder()
                .id(viewPermission.getId())
                .view(viewPermission.getViews() != null ? mapToResponseView(viewPermission.getViews()) : null)
                .permission(
                        viewPermission.getPermission() != null ? mapToResponsePermission(viewPermission.getPermission())
                                : null)
                .update(viewPermission.getUpdate())
                .delete(viewPermission.getDelete())
                .lead(viewPermission.getLead())
                .print(viewPermission.getPrint())
                .read(viewPermission.getRead())
                .write(viewPermission.getWrite())
                .name(viewPermission.getViews() != null && viewPermission.getPermission() != null ? viewPermission.getViews().getName() + " - " + viewPermission.getPermission().getName() : null)
                .build();
    }

    private PermissionResponse mapToResponsePermission(Permission permission) {
        return PermissionResponse.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .endpointBase(permission.getEndpointBase())
                .build();
    }

    private ViewResponse mapToResponseView(Views views) {
        if (views == null) {
            return null;
        }
        return ViewResponse.builder()
                .id(views.getId())
                .active(views.getActive())
                .name(views.getName())
                .route(views.getRoute())
                .icon(views.getIcon())
                .father(mapToResponseView(views.getFather()))
                .build();
    }

    private RolResponse mapToResponseRole(Rol rol) {
        return RolResponse.builder()
                .id(rol.getId())
                .name(rol.getName())
                .description(rol.getDescription())
                .build();
    }
}

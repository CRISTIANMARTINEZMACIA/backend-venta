package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Permission;
import com.cristianmartinez.api.backendventa.Entity.ViewPermission;
import com.cristianmartinez.api.backendventa.Entity.Views;
import com.cristianmartinez.api.backendventa.Repository.ViewPermissionRepository;
import com.cristianmartinez.api.backendventa.dto.request.ViewPermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PermissionResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewPermissionResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewPermissionService {
    @Autowired
    private ViewPermissionRepository repository;

    public PaginationResponse<List<ViewPermissionResponse>> findAll(Pageable pageable) {

        Page<ViewPermission> viewPermissions = repository.findAll(pageable);
        return PaginationResponse.<List<ViewPermissionResponse>>builder()
                .count(viewPermissions.getTotalElements())
                .page(viewPermissions.getTotalPages())
                .content(viewPermissions.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();

    }

    public ViewPermissionResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public ViewPermissionResponse save(ViewPermissionRequest viewPermissionRequest) {
        return mapToResponse(repository.save(mapToEntity(viewPermissionRequest)));
    }

    public ViewPermissionResponse update(ViewPermissionRequest viewPermissionRequest, Long id) {
        ViewPermission entity = repository.findById(id).get();

        entity.setViews(Views.builder().id(viewPermissionRequest.getView()).build());
        entity.setPermission(Permission.builder().id(viewPermissionRequest.getPermission()).build());
        entity.setUpdate(viewPermissionRequest.getUpdate());
        entity.setDelete(viewPermissionRequest.getDelete());
        entity.setLead(viewPermissionRequest.getLead());
        entity.setPrint(viewPermissionRequest.getPrint());
        entity.setRead(viewPermissionRequest.getRead());
        entity.setWrite(viewPermissionRequest.getWrite());

        ViewPermission saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private ViewPermission mapToEntity(ViewPermissionRequest viewPermissionRequest) {
        return ViewPermission.builder()
                .views(Views.builder().id(viewPermissionRequest.getView()).build())
                .permission(Permission.builder().id(viewPermissionRequest.getPermission()).build())
                .update(viewPermissionRequest.getUpdate())
                .delete(viewPermissionRequest.getDelete())
                .lead(viewPermissionRequest.getLead())
                .print(viewPermissionRequest.getPrint())
                .read(viewPermissionRequest.getRead())
                .write(viewPermissionRequest.getWrite())
                .build();
    }

    private ViewPermissionResponse mapToResponse(ViewPermission viewPermission) {
        return ViewPermissionResponse.builder()
                .id(viewPermission.getId())
                .view(mapToResponseView(viewPermission.getViews()))
                .permission(mapToResponsePermission(viewPermission.getPermission()))
                .update(viewPermission.getUpdate())
                .delete(viewPermission.getDelete())
                .lead(viewPermission.getLead())
                .print(viewPermission.getPrint())
                .read(viewPermission.getRead())
                .write(viewPermission.getWrite())
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
}

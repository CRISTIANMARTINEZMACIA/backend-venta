package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.RolPermissionService;
import com.cristianmartinez.api.backendventa.dto.request.RolPermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolPermissionResponse;

import java.util.List;

@RestController
@RequestMapping("/api/rol-permissions")
public class RolPermissionController {
    @Autowired
    private RolPermissionService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<RolPermissionResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<RolPermissionResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<RolPermissionResponse>>>builder()
                .code(200).status(false).message("Rol permisos obtenidos").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<RolPermissionResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<RolPermissionResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<RolPermissionResponse>builder()
                .code(200).status(false).message("Rol permiso encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<RolPermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<RolPermissionResponse> create(@RequestBody RolPermissionRequest rolPermissionRequest) {
        try {
             return DefaultResponse.<RolPermissionResponse>builder()
                .code(200).status(false).message("Rol permiso creado exitosamente").body(service.save(rolPermissionRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<RolPermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<RolPermissionResponse> update(@PathVariable Long id, @RequestBody RolPermissionRequest rolPermissionRequest) {
        try {
            return DefaultResponse.<RolPermissionResponse>builder()
                .code(200).status(false).message("Rol permiso actualizado").body(service.update(rolPermissionRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<RolPermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Rol permiso eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

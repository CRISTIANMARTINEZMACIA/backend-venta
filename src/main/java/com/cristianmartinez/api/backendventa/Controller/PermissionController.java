package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.PermissionService;
import com.cristianmartinez.api.backendventa.dto.request.PermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PermissionResponse;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired
    private PermissionService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<PermissionResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<PermissionResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<PermissionResponse>>>builder()
                .code(200).status(false).message("Negocios obtenido").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<PermissionResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @GetMapping("/{id}")
    public DefaultResponse<PermissionResponse> getById(@PathVariable Long id) {
        try {
        return DefaultResponse.<PermissionResponse>builder()
                .code(200).status(false).message("Permiso encontrado").body(service.findById(id)).build();
                } catch (Exception e) {
           return DefaultResponse.<PermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @PostMapping
    public DefaultResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {
        try {
            return DefaultResponse.<PermissionResponse>builder()
                .code(200).status(false).message("Permiso creado").body(service.save(permissionRequest)).build();
        } catch (Exception e) {
           return DefaultResponse.<PermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PutMapping("/{id}")
    public DefaultResponse<PermissionResponse> update(@PathVariable Long id, @RequestBody PermissionRequest permissionRequest) {
        try {
            return DefaultResponse.<PermissionResponse>builder()
                .code(200).status(false).message("Permiso actualizado").body(service.update(permissionRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<PermissionResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Permiso eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

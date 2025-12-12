package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.ViewPermissionService;
import com.cristianmartinez.api.backendventa.dto.request.ViewPermissionRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewPermissionResponse;

import java.util.List;

@RestController
@RequestMapping("/api/view-permissions")
public class ViewPermissionController {
    @Autowired
    private ViewPermissionService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<ViewPermissionResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            PaginationResponse<List<ViewPermissionResponse>> users = service.findAll(pageable);
            return DefaultResponse.<PaginationResponse<List<ViewPermissionResponse>>>builder()
                    .code(200).status(false).message("Permisos de la vista obtenidos").body(users).build();
        } catch (Exception e) {
            return DefaultResponse.<PaginationResponse<List<ViewPermissionResponse>>>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @GetMapping("/{id}")
    public DefaultResponse<ViewPermissionResponse> getById(@PathVariable Long id) {
        try {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(200).status(false).message("Permiso de la vista encontrado").body(service.findById(id))
                    .build();
        } catch (Exception e) {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @PostMapping
    public DefaultResponse<ViewPermissionResponse> create(@RequestBody ViewPermissionRequest entity) {
        try {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(200).status(false).message("Permiso de la vista creado").body(service.save(entity)).build();
        } catch (Exception e) {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @PutMapping("/{id}")
    public DefaultResponse<ViewPermissionResponse> update(@PathVariable Long id,
            @RequestBody ViewPermissionRequest entity) {
        try {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(200).status(false).message("Permiso de la vista actualizado").body(service.update(entity, id))
                    .build();
        } catch (Exception e) {
            return DefaultResponse.<ViewPermissionResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {

        try {
            service.deleteById(id);
            return DefaultResponse.<Void>builder()
                    .code(200).status(false).message("Eliminado correctamente").body(null).build();
        } catch (Exception e) {
            return DefaultResponse.<Void>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }
}
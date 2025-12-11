package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.RolService;
import com.cristianmartinez.api.backendventa.dto.request.RolRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<RolResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<RolResponse>> roles = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<RolResponse>>>builder()
                .code(200).status(false).message("Roles obtenidos").body(roles).build();
        } catch (Exception e) {
            return DefaultResponse.<PaginationResponse<List<RolResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<RolResponse> getById(@PathVariable Long id) {
        try {
            return DefaultResponse.<RolResponse>builder()
                .code(200).status(false).message("Rol encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
           return DefaultResponse.<RolResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<RolResponse> create(@RequestBody RolRequest rolRequest) {
        try {
            return DefaultResponse.<RolResponse>builder()
                .code(200).status(false).message("Rol creado").body(service.save(rolRequest)).build();
        } catch (Exception e) {
           return DefaultResponse.<RolResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PutMapping("/{id}")
    public DefaultResponse<RolResponse> update(@PathVariable Long id, @RequestBody RolRequest rolRequest) {
      try {
            return DefaultResponse.<RolResponse>builder()
                .code(200).status(false).message("Rol actualizado").body(service.update(rolRequest, id)).build();
        } catch (Exception e) {
            return DefaultResponse.<RolResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        } 
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Rol eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }
}

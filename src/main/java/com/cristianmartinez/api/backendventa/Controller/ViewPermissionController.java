package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Entity.ViewPermission;
import com.cristianmartinez.api.backendventa.Services.ViewPermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/view-permissions")
public class ViewPermissionController {
    @Autowired
    private ViewPermissionService service;

    @GetMapping
    public List<ViewPermission> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViewPermission> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ViewPermission create(@RequestBody ViewPermission entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViewPermission> update(@PathVariable Long id, @RequestBody ViewPermission entity) {
        return service.findById(id)
                .map(existing -> {
                    entity.setId(id);
                    return ResponseEntity.ok(service.save(entity));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
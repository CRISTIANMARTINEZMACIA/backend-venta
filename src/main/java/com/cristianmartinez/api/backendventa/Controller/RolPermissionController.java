package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Entity.RolPermission;
import com.cristianmartinez.api.backendventa.Services.RolPermissionService;

import java.util.List;

@RestController
@RequestMapping("/api/rol-permissions")
public class RolPermissionController {
    @Autowired
    private RolPermissionService service;

    @GetMapping
    public List<RolPermission> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolPermission> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RolPermission create(@RequestBody RolPermission entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolPermission> update(@PathVariable Long id, @RequestBody RolPermission entity) {
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

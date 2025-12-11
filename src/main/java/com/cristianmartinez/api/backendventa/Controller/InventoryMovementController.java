package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Entity.InventoryMovement;
import com.cristianmartinez.api.backendventa.Services.InventoryMovementService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {
    @Autowired
    private InventoryMovementService service;

    @GetMapping
    public List<InventoryMovement> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovement> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventoryMovement create(@RequestBody InventoryMovement entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovement> update(@PathVariable Long id, @RequestBody InventoryMovement entity) {
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
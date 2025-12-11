package com.cristianmartinez.api.backendventa.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Entity.SaleDetail;
import com.cristianmartinez.api.backendventa.Services.SaleDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/sale-details")
public class SaleDetailController {
    @Autowired
    private SaleDetailService service;

    @GetMapping
    public List<SaleDetail> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetail> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SaleDetail create(@RequestBody SaleDetail entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetail> update(@PathVariable Long id, @RequestBody SaleDetail entity) {
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

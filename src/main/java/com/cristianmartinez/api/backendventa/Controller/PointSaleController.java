package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Entity.PointSale;
import com.cristianmartinez.api.backendventa.Services.PointSaleService;

import java.util.List;

@RestController
@RequestMapping("/api/point-sales")
public class PointSaleController {
    @Autowired
    private PointSaleService service;

    @GetMapping
    public List<PointSale> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PointSale> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PointSale create(@RequestBody PointSale entity) {
        return service.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PointSale> update(@PathVariable Long id, @RequestBody PointSale entity) {
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

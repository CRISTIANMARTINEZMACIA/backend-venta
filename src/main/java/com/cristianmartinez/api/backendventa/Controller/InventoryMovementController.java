package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.InventoryMovementService;
import com.cristianmartinez.api.backendventa.dto.request.InventoryMovementRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.InventoryMovementResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {
    @Autowired
    private InventoryMovementService service;

        @GetMapping
    public DefaultResponse<PaginationResponse<List<InventoryMovementResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<InventoryMovementResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<InventoryMovementResponse>>>builder()
                .code(200).status(false).message("Movimientos del inventario obtenidos").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<InventoryMovementResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<InventoryMovementResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<InventoryMovementResponse>builder()
                .code(200).status(false).message("Movimiento del inventario encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<InventoryMovementResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<InventoryMovementResponse> create(@RequestBody InventoryMovementRequest inventoryMovementRequest) {
        try {
             return DefaultResponse.<InventoryMovementResponse>builder()
                .code(200).status(false).message("Movimiento del inventario creado exitosamente").body(service.save(inventoryMovementRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<InventoryMovementResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<InventoryMovementResponse> update(@PathVariable Long id, @RequestBody InventoryMovementRequest inventoryMovementRequest) {
        try {
            return DefaultResponse.<InventoryMovementResponse>builder()
                .code(200).status(false).message("Movimiento del inventario actualizado").body(service.update(inventoryMovementRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<InventoryMovementResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Movimiento del inventario eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}
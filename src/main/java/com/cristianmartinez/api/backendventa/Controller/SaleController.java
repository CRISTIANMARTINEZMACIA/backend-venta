package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import com.cristianmartinez.api.backendventa.Services.SaleService;
import com.cristianmartinez.api.backendventa.dto.request.SaleRequest;
import com.cristianmartinez.api.backendventa.dto.request.TransactionalSaveRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.SaleResponse;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleService service;

   @GetMapping
    public DefaultResponse<PaginationResponse<List<SaleResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<SaleResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<SaleResponse>>>builder()
                .code(200).status(false).message("Ventas obtenidas").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<SaleResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<SaleResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<SaleResponse>builder()
                .code(200).status(false).message("Venta encontrada").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<SaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<SaleResponse> create(@RequestBody SaleRequest saleRequest) {
        try {
             return DefaultResponse.<SaleResponse>builder()
                .code(200).status(false).message("Venta creada exitosamente").body(service.save(saleRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<SaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }
     @PostMapping("/create")
    public DefaultResponse<SaleResponse> transactionalCreate(@RequestBody TransactionalSaveRequest transactionalSaveRequest) {
        try {
             return DefaultResponse.<SaleResponse>builder()
                .code(200).status(false).message("Venta creada exitosamente").body(service.transactionalSave(transactionalSaveRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<SaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<SaleResponse> update(@PathVariable Long id, @RequestBody SaleRequest saleRequest) {
        try {
            return DefaultResponse.<SaleResponse>builder()
                .code(200).status(false).message("Venta actualizada").body(service.update(saleRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<SaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Venta eliminada").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

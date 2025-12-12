package com.cristianmartinez.api.backendventa.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.SaleDetailService;
import com.cristianmartinez.api.backendventa.dto.request.SaleDetailRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.SaleDetailResponse;

import java.util.List;

@RestController
@RequestMapping("/api/sale-details")
public class SaleDetailController {
    @Autowired
    private SaleDetailService service;

       @GetMapping
    public DefaultResponse<PaginationResponse<List<SaleDetailResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<SaleDetailResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<SaleDetailResponse>>>builder()
                .code(200).status(false).message("Detalle de ventas obtenida").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<SaleDetailResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<SaleDetailResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<SaleDetailResponse>builder()
                .code(200).status(false).message("Detalle de venta encontrada").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<SaleDetailResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<SaleDetailResponse> create(@RequestBody SaleDetailRequest saleDetailRequest) {
        try {
             return DefaultResponse.<SaleDetailResponse>builder()
                .code(200).status(false).message("Detalle de venta creada exitosamente").body(service.save(saleDetailRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<SaleDetailResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<SaleDetailResponse> update(@PathVariable Long id, @RequestBody SaleDetailRequest saleDetailRequest) {
        try {
            return DefaultResponse.<SaleDetailResponse>builder()
                .code(200).status(false).message("Detalle de venta actualizada").body(service.update(saleDetailRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<SaleDetailResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Detalle de venta eliminada").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

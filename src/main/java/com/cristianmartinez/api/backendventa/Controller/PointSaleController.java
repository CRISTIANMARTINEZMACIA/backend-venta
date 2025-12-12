package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.PointSaleService;
import com.cristianmartinez.api.backendventa.dto.request.PointSaleRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PointSaleResponse;

import java.util.List;

@RestController
@RequestMapping("/api/point-sales")
public class PointSaleController {
    @Autowired
    private PointSaleService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<PointSaleResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<PointSaleResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<PointSaleResponse>>>builder()
                .code(200).status(false).message("Punto de ventas obtenidos").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<PointSaleResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<PointSaleResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<PointSaleResponse>builder()
                .code(200).status(false).message("Punto de venta encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<PointSaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<PointSaleResponse> create(@RequestBody PointSaleRequest pointSaleRequest) {
        try {
             return DefaultResponse.<PointSaleResponse>builder()
                .code(200).status(false).message("Punto de venta creado exitosamente").body(service.save(pointSaleRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<PointSaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<PointSaleResponse> update(@PathVariable Long id, @RequestBody PointSaleRequest pointSaleRequest) {
        try {
            return DefaultResponse.<PointSaleResponse>builder()
                .code(200).status(false).message("Punto de venta actualizado").body(service.update(pointSaleRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<PointSaleResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Punto de venta eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

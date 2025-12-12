package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.StockService;
import com.cristianmartinez.api.backendventa.dto.request.StockRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.StockResponse;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    @Autowired
    private StockService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<StockResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<StockResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<StockResponse>>>builder()
                .code(200).status(false).message("Stocks obtenidos").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<StockResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<StockResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<StockResponse>builder()
                .code(200).status(false).message("Stock encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<StockResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<StockResponse> create(@RequestBody StockRequest stockRequest) {
        try {
             return DefaultResponse.<StockResponse>builder()
                .code(200).status(false).message("Stock creado exitosamente").body(service.save(stockRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<StockResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<StockResponse> update(@PathVariable Long id, @RequestBody StockRequest stockRequest) {
        try {
            return DefaultResponse.<StockResponse>builder()
                .code(200).status(false).message("Stock actualizado").body(service.update(stockRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<StockResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Stock eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

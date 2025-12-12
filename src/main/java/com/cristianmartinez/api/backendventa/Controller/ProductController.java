package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.ProductService;
import com.cristianmartinez.api.backendventa.dto.request.ProductRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<ProductResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            PaginationResponse<List<ProductResponse>> businesses = service.findAll(pageable);
            return DefaultResponse.<PaginationResponse<List<ProductResponse>>>builder()
                    .code(200).status(false).message("Productos obtenido").body(businesses).build();
        } catch (Exception e) {
            return DefaultResponse.<PaginationResponse<List<ProductResponse>>>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @GetMapping("/{id}")
    public DefaultResponse<ProductResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<ProductResponse>builder()
                    .code(200).status(false).message("Producto encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<ProductResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @PostMapping
    public DefaultResponse<ProductResponse> create(@RequestBody ProductRequest productRequest) {
        try {
            return DefaultResponse.<ProductResponse>builder()
                    .code(200).status(false).message("Producto creado exitosamente").body(service.save(productRequest))
                    .build();
        } catch (Exception e) {
            return DefaultResponse.<ProductResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @PutMapping("/{id}")
    public DefaultResponse<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        try {
            return DefaultResponse.<ProductResponse>builder()
                    .code(200).status(false).message("Producto actualizado").body(service.update(productRequest, id))
                    .build();
        } catch (Exception e) {
            return DefaultResponse.<ProductResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return DefaultResponse.<Void>builder()
                    .code(200).status(false).message("Producto eliminado").body(null).build();
        } catch (Exception e) {
            return DefaultResponse.<Void>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }
}

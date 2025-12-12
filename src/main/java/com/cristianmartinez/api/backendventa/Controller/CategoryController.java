package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.CategoryService;
import com.cristianmartinez.api.backendventa.dto.request.CategoryRequest;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

   @GetMapping
    public DefaultResponse<PaginationResponse<List<CategoryResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<CategoryResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<CategoryResponse>>>builder()
                .code(200).status(false).message("Categorias obtenidas").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<CategoryResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<CategoryResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<CategoryResponse>builder()
                .code(200).status(false).message("Categoria encontrada").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<CategoryResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        try {
             return DefaultResponse.<CategoryResponse>builder()
                .code(200).status(false).message("Categoria creada exitosamente").body(service.save(categoryRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<CategoryResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        try {
            return DefaultResponse.<CategoryResponse>builder()
                .code(200).status(false).message("Categoria actualizada").body(service.update(categoryRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<CategoryResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Categoria eliminada").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

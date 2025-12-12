package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.RecipeService;
import com.cristianmartinez.api.backendventa.dto.request.RecipeRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.RecipeResponse;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService service;

       @GetMapping
    public DefaultResponse<PaginationResponse<List<RecipeResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<RecipeResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<RecipeResponse>>>builder()
                .code(200).status(false).message("Recetas obtenidas").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<RecipeResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<RecipeResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<RecipeResponse>builder()
                .code(200).status(false).message("Receta encontrada").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<RecipeResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<RecipeResponse> create(@RequestBody RecipeRequest recipeRequest) {
        try {
             return DefaultResponse.<RecipeResponse>builder()
                .code(200).status(false).message("Receta creada exitosamente").body(service.save(recipeRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<RecipeResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<RecipeResponse> update(@PathVariable Long id, @RequestBody RecipeRequest recipeRequest) {
        try {
            return DefaultResponse.<RecipeResponse>builder()
                .code(200).status(false).message("Receta actualizada").body(service.update(recipeRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<RecipeResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Receta eliminada").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.IngredientService;
import com.cristianmartinez.api.backendventa.dto.request.IngredientRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.IngredientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<IngredientResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            PaginationResponse<List<IngredientResponse>> businesses = service.findAll(pageable);
            return DefaultResponse.<PaginationResponse<List<IngredientResponse>>>builder()
                    .code(200).status(false).message("Ingredientes obtenido").body(businesses).build();
        } catch (Exception e) {
            return DefaultResponse.<PaginationResponse<List<IngredientResponse>>>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @GetMapping("/{id}")
    public DefaultResponse<IngredientResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(200).status(false).message("Ingrediente encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @PostMapping
    public DefaultResponse<IngredientResponse> create(@RequestBody IngredientRequest ingredientRequest) {
        try {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(200).status(false).message("Ingrediente creado exitosamente")
                    .body(service.save(ingredientRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @PutMapping("/{id}")
    public DefaultResponse<IngredientResponse> update(@PathVariable Long id,
            @RequestBody IngredientRequest ingredientRequest) {
        try {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(200).status(false).message("Ingrediente actualizado")
                    .body(service.update(ingredientRequest, id)).build();
        } catch (Exception e) {
            return DefaultResponse.<IngredientResponse>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return DefaultResponse.<Void>builder()
                    .code(200).status(false).message("Ingrediente eliminado").body(null).build();
        } catch (Exception e) {
            return DefaultResponse.<Void>builder()
                    .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }

    }
}

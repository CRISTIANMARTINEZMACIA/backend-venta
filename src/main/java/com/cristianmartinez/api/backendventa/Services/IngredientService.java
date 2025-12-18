package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.Ingredient;
import com.cristianmartinez.api.backendventa.Repository.IngredientRepository;
import com.cristianmartinez.api.backendventa.dto.request.IngredientRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.IngredientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository repository;

    public PaginationResponse<List<IngredientResponse>> findAll(Pageable pageable) {
        Page<Ingredient> business = repository.findAll(pageable);
        return PaginationResponse.<List<IngredientResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public IngredientResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public IngredientResponse save(IngredientRequest ingredientRequest) {
        return mapToResponse(repository.save(mapToEntity(ingredientRequest)));
    }

     public IngredientResponse update(IngredientRequest ingredientRequest, Long id) {
           
        Ingredient entity = repository.findById(id).get();

        entity.setName(ingredientRequest.getName());
        entity.setCategory(Category.builder().id(ingredientRequest.getCategory()).build());
        entity.setUnitMeasure(ingredientRequest.getUnitMeasure());

        Ingredient saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Ingredient mapToEntity(IngredientRequest ingredientRequest) {
        return Ingredient.builder()
                .name(ingredientRequest.getName())
                .category(Category.builder().id(ingredientRequest.getCategory()).build())
                .unitMeasure(ingredientRequest.getUnitMeasure())
                .build();
    }

    private IngredientResponse mapToResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnitMeasure())
                .category(ingredient.getCategory() != null ? mapToResponseCategory(ingredient.getCategory()) : null)
                .build();
    }

    private CategoryResponse mapToResponseCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .business(mapToResponseBusiness(category.getBusiness()))

                .build();
    }

    private BusinessResponse mapToResponseBusiness(Business business) {
        return BusinessResponse.builder()
                .id(business.getId())
                .name(business.getName())
                .direction(business.getDirection())
                .phone(business.getPhone())
                .typeBusiness(business.getTypeBusiness())
                .rucNit(business.getRucNit())
                .build();
    }
}

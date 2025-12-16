package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.Ingredient;
import com.cristianmartinez.api.backendventa.Entity.Product;
import com.cristianmartinez.api.backendventa.Entity.Recipe;
import com.cristianmartinez.api.backendventa.Repository.RecipeRepository;
import com.cristianmartinez.api.backendventa.dto.request.RecipeRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.IngredientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;
import com.cristianmartinez.api.backendventa.dto.response.RecipeResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    public PaginationResponse<List<RecipeResponse>> findAll(Pageable pageable) {
        Page<Recipe> business = repository.findAll(pageable);
        return PaginationResponse.<List<RecipeResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public RecipeResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public RecipeResponse save(RecipeRequest recipeRequest) {
        return mapToResponse(repository.save(mapToEntity(recipeRequest)));
    }

    public RecipeResponse update(RecipeRequest recipeRequest, Long id) {

        Recipe entity = repository.findById(id).get();

        entity.setProduct(Product.builder().id(recipeRequest.getProduct()).build());
        entity.setIngredient(Ingredient.builder().id(recipeRequest.getIngredient()).build());
        entity.setAmountNeed(recipeRequest.getAmountNeed());

        Recipe saved = repository.save(entity);
        return mapToResponse(saved);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Recipe mapToEntity(RecipeRequest recipeRequest) {
        return Recipe.builder()
                .product(Product.builder().id(recipeRequest.getProduct()).build())
                .ingredient(Ingredient.builder().id(recipeRequest.getIngredient()).build())
                .amountNeed(recipeRequest.getAmountNeed())
                .build();
    }

    private RecipeResponse mapToResponse(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .product(mapToResponseProduct(recipe.getProduct()))
                .ingredient(mapToResponseIngredient(recipe.getIngredient()))
                .amountNeed(recipe.getAmountNeed())
                .build();
    }

    private IngredientResponse mapToResponseIngredient(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unit(ingredient.getUnitMeasure())
                .category(mapToResponseCategory(ingredient.getCategory()))
                .build();
    }

    private ProductResponse mapToResponseProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .priceSale(product.getPriceSale())
                .category(mapToResponseCategory(product.getCategory()))
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

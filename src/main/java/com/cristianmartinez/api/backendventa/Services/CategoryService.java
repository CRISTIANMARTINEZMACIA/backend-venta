package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Repository.CategoryRepository;
import com.cristianmartinez.api.backendventa.dto.request.CategoryRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public PaginationResponse<List<CategoryResponse>> findAll(Pageable pageable) {
        Page<Category> business = repository.findAll(pageable);
        return PaginationResponse.<List<CategoryResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public CategoryResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public CategoryResponse save(CategoryRequest categoryRequest) {
        return mapToResponse(repository.save(mapToEntity(categoryRequest)));
    }

    public CategoryResponse update(CategoryRequest categoryRequest, Long id) {
        Category entity = repository.findById(id).get();

        entity.setName(categoryRequest.getName());
        entity.setBusiness(Business.builder().id(categoryRequest.getBusiness()).build());

        Category saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Category mapToEntity(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .business(Business.builder().id(categoryRequest.getBusiness()).build())
                .build();
    }

    private CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .business(category.getBusiness() != null ? mapToResponseBusiness(category.getBusiness()) : null)
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
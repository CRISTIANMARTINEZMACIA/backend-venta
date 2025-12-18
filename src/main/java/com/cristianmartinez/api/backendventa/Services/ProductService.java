package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.Product;
import com.cristianmartinez.api.backendventa.Repository.ProductRepository;
import com.cristianmartinez.api.backendventa.dto.request.ProductRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public PaginationResponse<List<ProductResponse>> findAll(Pageable pageable) {
        Page<Product> business = repository.findAll(pageable);
        return PaginationResponse.<List<ProductResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public ProductResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public ProductResponse save(ProductRequest productRequest) {
        return mapToResponse(repository.save(mapToEntity(productRequest)));
    }

    public ProductResponse update(ProductRequest productRequest, Long id) {
           
        Product entity = repository.findById(id).get();

        entity.setName(productRequest.getName());
        entity.setDescription(productRequest.getDescription());
        entity.setCategory(Category.builder().id(productRequest.getCategory()).build());
        entity.setPriceSale(productRequest.getPriceSale());
        entity.setIs_inventariable(productRequest.getIs_inventariable());

        Product saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Product mapToEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .priceSale(productRequest.getPriceSale())
                .category(Category.builder().id(productRequest.getCategory()).build())
                .is_inventariable(productRequest.getIs_inventariable())
                .build();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .priceSale(product.getPriceSale())
                .category(product.getCategory() != null ? mapToResponseCategory(product.getCategory()) : null)
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

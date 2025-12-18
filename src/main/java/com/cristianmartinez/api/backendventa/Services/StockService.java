package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.Product;
import com.cristianmartinez.api.backendventa.Entity.Stock;
import com.cristianmartinez.api.backendventa.Repository.StockRepository;
import com.cristianmartinez.api.backendventa.dto.request.StockRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;
import com.cristianmartinez.api.backendventa.dto.response.StockResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    @Autowired
    private StockRepository repository;

    public PaginationResponse<List<StockResponse>> findAll(Pageable pageable) {
        Page<Stock> business = repository.findAll(pageable);
        return PaginationResponse.<List<StockResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public StockResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public StockResponse save(StockRequest stockRequest) {
        return mapToResponse(repository.save(mapToEntity(stockRequest)));
    }

    public StockResponse update(StockRequest stockRequest, Long id) {

        Stock entity = repository.findById(id).get();

        entity.setAmountCurrent(stockRequest.getAmountCurrent());
        entity.setProduct(Product.builder().id(stockRequest.getProduct()).build());
        entity.setUnitMeasure(stockRequest.getUnitMeasure());
        entity.setStockMin(stockRequest.getStockMin());

        Stock saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Stock mapToEntity(StockRequest stockRequest) {
        return Stock.builder()
                .product(Product.builder().id(stockRequest.getProduct()).build())
                .amountCurrent(stockRequest.getAmountCurrent())
                .unitMeasure(stockRequest.getUnitMeasure())
                .stockMin(stockRequest.getStockMin())
                .build();
    }

    private StockResponse mapToResponse(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .product(stock.getProduct() != null ? mapToResponseProduct(stock.getProduct()) : null)
                .amountCurrent(stock.getAmountCurrent())
                .unitMeasure(stock.getUnitMeasure())
                .stockMin(stock.getStockMin())
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

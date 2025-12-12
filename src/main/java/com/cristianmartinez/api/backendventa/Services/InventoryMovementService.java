package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.InventoryMovement;
import com.cristianmartinez.api.backendventa.Entity.Product;
import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Entity.Stock;
import com.cristianmartinez.api.backendventa.Entity.User;
import com.cristianmartinez.api.backendventa.Repository.InventoryMovementRepository;
import com.cristianmartinez.api.backendventa.dto.request.InventoryMovementRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.InventoryMovementResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;
import com.cristianmartinez.api.backendventa.dto.response.StockResponse;
import com.cristianmartinez.api.backendventa.dto.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryMovementService {
    @Autowired
    private InventoryMovementRepository repository;

    public PaginationResponse<List<InventoryMovementResponse>> findAll(Pageable pageable) {
        Page<InventoryMovement> business = repository.findAll(pageable);
        return PaginationResponse.<List<InventoryMovementResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public InventoryMovementResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public InventoryMovementResponse save(InventoryMovementRequest inventoryMovementRequest) {
        return mapToResponse(repository.save(mapToEntity(inventoryMovementRequest)));
    }

    public InventoryMovementResponse update(InventoryMovementRequest inventoryMovementRequest, Long id) {

        InventoryMovement entity = repository.findById(id).get();

        entity.setStock(Stock.builder().id(inventoryMovementRequest.getStock()).build());
        entity.setUser(User.builder().id(inventoryMovementRequest.getUser()).build());
        entity.setAmount(inventoryMovementRequest.getAmount());
        entity.setTypeMovement(inventoryMovementRequest.getTypeMovement());
        entity.setDateMovement(inventoryMovementRequest.getDateMovement());

        InventoryMovement saved = repository.save(entity);
        return mapToResponse(saved);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private InventoryMovement mapToEntity(InventoryMovementRequest inventoryMovementRequest) {
        return InventoryMovement.builder()
                .stock(Stock.builder().id(inventoryMovementRequest.getStock()).build())
                .user(User.builder().id(inventoryMovementRequest.getUser()).build())
                .amount(inventoryMovementRequest.getAmount())
                .typeMovement(inventoryMovementRequest.getTypeMovement())
                .dateMovement(inventoryMovementRequest.getDateMovement())
                .build();
    }

    private InventoryMovementResponse mapToResponse(InventoryMovement inventoryMoment) {
        return InventoryMovementResponse.builder()
                .id(inventoryMoment.getId())
                .stock(mapToResponseStock(inventoryMoment.getStock()))
                .typeMovement(inventoryMoment.getTypeMovement().toString())
                .amount(inventoryMoment.getAmount())
                .dateMovement(inventoryMoment.getDateMovement())
                .user(mapToResponseUser(inventoryMoment.getUser()))
                .build();
    }

    private StockResponse mapToResponseStock(Stock stock) {
        return StockResponse.builder()
                .id(stock.getId())
                .product(mapToResponseProduct(stock.getProduct()))
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
                .business(mapToResponseBusiness(product.getBusiness()))
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

    private UserResponse mapToResponseUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .business(mapToResponseBusiness(user.getBusiness()))
                .rol(mapToResponseRole(user.getRol()))
                .build();
    }

    private RolResponse mapToResponseRole(Rol rol) {
        return RolResponse.builder()
                .id(rol.getId())
                .name(rol.getName())
                .description(rol.getDescription())
                .build();
    }
}

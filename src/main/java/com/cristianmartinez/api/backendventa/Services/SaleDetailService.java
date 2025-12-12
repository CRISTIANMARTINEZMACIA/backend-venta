package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Category;
import com.cristianmartinez.api.backendventa.Entity.Client;
import com.cristianmartinez.api.backendventa.Entity.PointSale;
import com.cristianmartinez.api.backendventa.Entity.Product;
import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Entity.Sale;
import com.cristianmartinez.api.backendventa.Entity.SaleDetail;
import com.cristianmartinez.api.backendventa.Entity.User;
import com.cristianmartinez.api.backendventa.Repository.SaleDetailRepository;
import com.cristianmartinez.api.backendventa.dto.request.SaleDetailRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.CategoryResponse;
import com.cristianmartinez.api.backendventa.dto.response.ClientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PointSaleResponse;
import com.cristianmartinez.api.backendventa.dto.response.ProductResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;
import com.cristianmartinez.api.backendventa.dto.response.SaleDetailResponse;
import com.cristianmartinez.api.backendventa.dto.response.SaleResponse;
import com.cristianmartinez.api.backendventa.dto.response.UserResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository repository;

   public PaginationResponse<List<SaleDetailResponse>> findAll(Pageable pageable) {

        Page<SaleDetail> viewPermissions = repository.findAll(pageable);
        return PaginationResponse.<List<SaleDetailResponse>>builder()
                .count(viewPermissions.getTotalElements())
                .page(viewPermissions.getTotalPages())
                .content(viewPermissions.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();

    }

    public SaleDetailResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public SaleDetailResponse save(SaleDetailRequest saleDetailRequest) {
        return mapToResponse(repository.save(mapToEntity(saleDetailRequest)));
    }

    public SaleDetailResponse update(SaleDetailRequest saleDetailRequest, Long id) {
           
        SaleDetail entity = repository.findById(id).get();

        entity.setSale(Sale.builder().id(saleDetailRequest.getSale()).build());
        entity.setProduct(Product.builder().id(saleDetailRequest.getProduct()).build());
        entity.setAmount(saleDetailRequest.getAmount());
        entity.setPriceUnitary(saleDetailRequest.getPriceUnitary());
        entity.setSubtotal(new BigDecimal((saleDetailRequest.getAmount().doubleValue()*saleDetailRequest.getPriceUnitary().doubleValue())));

        SaleDetail saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

     private SaleDetail mapToEntity(SaleDetailRequest saleDetailRequest) {
        return SaleDetail.builder()
                .sale(Sale.builder().id(saleDetailRequest.getSale()).build())
                .product(Product.builder().id(saleDetailRequest.getProduct()).build())
                .amount(saleDetailRequest.getAmount())
                .priceUnitary(saleDetailRequest.getPriceUnitary())
                .subtotal(new BigDecimal((saleDetailRequest.getAmount().doubleValue()*saleDetailRequest.getPriceUnitary().doubleValue())))
                .build();
    }

    private SaleDetailResponse mapToResponse(SaleDetail saleDetail) {
        return SaleDetailResponse.builder()
                .id(saleDetail.getId())
                .sale(mapToResponseSale(saleDetail.getSale()))
                .product(mapToResponseProduct(saleDetail.getProduct()))
                .amount(saleDetail.getAmount())
                .priceUnitary(saleDetail.getPriceUnitary())
                .subtotal(saleDetail.getSubtotal())
                .build();
    }

    private SaleResponse mapToResponseSale(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .business(mapToResponseBusiness(sale.getBusiness()))
                .pointSale(mapToResponsePointSale(sale.getPointSale()))
                .user(mapToResponseUser(sale.getUser()))
                .client(mapToResponseClient(sale.getClient()))
                .dateSale(sale.getDateSale())
                .total(sale.getTotal())
                .typePayment(sale.getTypePayment())
                .status(sale.getStatus().toString())
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

    private ClientResponse mapToResponseClient(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .business(mapToResponseBusiness(client.getBusiness()))
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

    private PointSaleResponse mapToResponsePointSale(PointSale pointSale) {
        return PointSaleResponse.builder()
                .id(pointSale.getId())
                .name(pointSale.getName())
                .business(mapToResponseBusiness(pointSale.getBusiness()))
                .active(pointSale.getActive())
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
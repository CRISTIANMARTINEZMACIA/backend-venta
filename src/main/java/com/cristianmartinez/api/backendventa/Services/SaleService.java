package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Client;
import com.cristianmartinez.api.backendventa.Entity.PointSale;
import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Entity.Sale;
import com.cristianmartinez.api.backendventa.Entity.User;
import com.cristianmartinez.api.backendventa.Repository.SaleRepository;
import com.cristianmartinez.api.backendventa.dto.request.SaleRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.ClientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PointSaleResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;
import com.cristianmartinez.api.backendventa.dto.response.SaleResponse;
import com.cristianmartinez.api.backendventa.dto.response.UserResponse;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    public PaginationResponse<List<SaleResponse>> findAll(Pageable pageable) {

        Page<Sale> viewPermissions = repository.findAll(pageable);
        return PaginationResponse.<List<SaleResponse>>builder()
                .count(viewPermissions.getTotalElements())
                .page(viewPermissions.getTotalPages())
                .content(viewPermissions.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();

    }

    public SaleResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public SaleResponse save(SaleRequest saleRequest) {
        return mapToResponse(repository.save(mapToEntity(saleRequest)));
    }

    public SaleResponse update(SaleRequest saleRequest, Long id) {
           
        Sale entity = repository.findById(id).get();

        entity.setClient(Client.builder().id(saleRequest.getClient()).build());
        entity.setDateSale(saleRequest.getDateSale());
        entity.setPointSale(PointSale.builder().id(saleRequest.getPointSale()).build());
        entity.setUser(User.builder().id(saleRequest.getUser()).build());
        entity.setStatus(saleRequest.getStatus());
        entity.setTotal(saleRequest.getTotal());
        entity.setTypePayment(saleRequest.getTypePayment());

        Sale saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Sale mapToEntity(SaleRequest saleRequest) {
        return Sale.builder()
                .client(Client.builder().id(saleRequest.getClient()).build())
                .pointSale(PointSale.builder().id(saleRequest.getPointSale()).build())
                .user(User.builder().id(saleRequest.getUser()).build())
                .total(saleRequest.getTotal())
                .typePayment(saleRequest.getTypePayment())
                .status(saleRequest.getStatus())
                .dateSale(saleRequest.getDateSale())
                .build();
    }

    private SaleResponse mapToResponse(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .pointSale(mapToResponsePointSale(sale.getPointSale()))
                .user(mapToResponseUser(sale.getUser()))
                .client(mapToResponseClient(sale.getClient()))
                .dateSale(sale.getDateSale())
                .total(sale.getTotal())
                .typePayment(sale.getTypePayment())
                .status(sale.getStatus().toString())
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

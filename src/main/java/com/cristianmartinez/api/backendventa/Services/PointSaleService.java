package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.PointSale;
import com.cristianmartinez.api.backendventa.Repository.PointSaleRepository;
import com.cristianmartinez.api.backendventa.dto.request.PointSaleRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.PointSaleResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointSaleService {
    @Autowired
    private PointSaleRepository repository;

   public PaginationResponse<List<PointSaleResponse>> findAll(Pageable pageable) {
        Page<PointSale> business = repository.findAll(pageable);
        return PaginationResponse.<List<PointSaleResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public PointSaleResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public PointSaleResponse save(PointSaleRequest pointSaleRequest) {
        return mapToResponse(repository.save(mapToEntity(pointSaleRequest)));
    }

    public PointSaleResponse update(PointSaleRequest pointSaleRequest, Long id) {
           
        PointSale entity = repository.findById(id).get();

        entity.setName(pointSaleRequest.getName());
        entity.setBusiness(Business.builder().id(pointSaleRequest.getBusiness()).build());
        entity.setActive(pointSaleRequest.getActive());

        PointSale saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

     private PointSale mapToEntity(PointSaleRequest pointSaleRequest) {
        return PointSale.builder()
                .name(pointSaleRequest.getName())
                .business(Business.builder().id(pointSaleRequest.getBusiness()).build())
                .build();
    }

    private PointSaleResponse mapToResponse(PointSale pointSale) {
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

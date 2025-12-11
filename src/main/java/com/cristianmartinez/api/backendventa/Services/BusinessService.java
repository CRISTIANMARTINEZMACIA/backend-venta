package com.cristianmartinez.api.backendventa.Services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Repository.BusinessRepository;
import com.cristianmartinez.api.backendventa.dto.request.BusinessRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessService {
    @Autowired
    private BusinessRepository repository;

    public PaginationResponse<List<BusinessResponse>> findAll(Pageable pageable) {

        Page<Business> business = repository.findAll(pageable);
         return PaginationResponse.<List<BusinessResponse>>builder()
         .count(business.getTotalElements())
         .page(business.getTotalPages())
         .content(business.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList()))
         .build();

         
    }

    public BusinessResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public BusinessResponse save(BusinessRequest businessRequest) {
        return mapToResponse(repository.save(mapToEntity(businessRequest)));
    }

    public BusinessResponse update(BusinessRequest businessRequest, Long id) {
           
        Business entity = repository.findById(id).get();

        entity.setName(businessRequest.getName());
        entity.setTypeBusiness(businessRequest.getTypeBusiness());
        entity.setDirection(businessRequest.getDirection());
        entity.setPhone(businessRequest.getPhone());
        entity.setRucNit(businessRequest.getRucNit());

        Business saved = repository.save(entity);
        return mapToResponse(saved);
    
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Business mapToEntity(BusinessRequest businessRequest) {
        return Business.builder()
                .name(businessRequest.getName())
                .typeBusiness(businessRequest.getTypeBusiness())
                .direction(businessRequest.getDirection())
                .phone(businessRequest.getPhone())
                .rucNit(businessRequest.getRucNit())
                .build();
    }

    

    private BusinessResponse mapToResponse(Business business) {
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

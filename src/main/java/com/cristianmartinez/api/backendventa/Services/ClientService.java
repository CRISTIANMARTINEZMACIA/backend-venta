package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Client;
import com.cristianmartinez.api.backendventa.Repository.ClientRepository;
import com.cristianmartinez.api.backendventa.dto.request.ClientRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.ClientResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public PaginationResponse<List<ClientResponse>> findAll(Pageable pageable) {
        Page<Client> business = repository.findAll(pageable);
        return PaginationResponse.<List<ClientResponse>>builder()
                .count(business.getTotalElements())
                .page(business.getTotalPages())
                .content(business.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public ClientResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public ClientResponse save(ClientRequest clientRequest) {
        return mapToResponse(repository.save(mapToEntity(clientRequest)));
    }

    public ClientResponse update(ClientRequest clientRequest, Long id) {

        Client entity = repository.findById(id).get();

        entity.setName(clientRequest.getName());
        entity.setBusiness(Business.builder().id(clientRequest.getBusiness()).build());
        entity.setEmail(clientRequest.getEmail());
        entity.setPhone(clientRequest.getPhone());
        entity.setLastName(clientRequest.getLastName());

        Client saved = repository.save(entity);
        return mapToResponse(saved);

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Client mapToEntity(ClientRequest clientRequest) {
        return Client.builder()
                .name(clientRequest.getName())
                .lastName(clientRequest.getLastName())
                .phone(clientRequest.getPhone())
                .email(clientRequest.getEmail())
                .business(Business.builder().id(clientRequest.getBusiness()).build())
                .build();
    }

    private ClientResponse mapToResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .business(client.getBusiness() != null ? mapToResponseBusiness(client.getBusiness()) : null)
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

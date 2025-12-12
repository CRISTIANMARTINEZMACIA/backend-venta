package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.ClientService;
import com.cristianmartinez.api.backendventa.dto.request.ClientRequest;
import com.cristianmartinez.api.backendventa.dto.response.ClientResponse;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService service;

      @GetMapping
    public DefaultResponse<PaginationResponse<List<ClientResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<ClientResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<ClientResponse>>>builder()
                .code(200).status(false).message("Clientes obtenidos").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<ClientResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<ClientResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<ClientResponse>builder()
                .code(200).status(false).message("Cliente encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<ClientResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<ClientResponse> create(@RequestBody ClientRequest clientRequest) {
        try {
             return DefaultResponse.<ClientResponse>builder()
                .code(200).status(false).message("Cliente creado exitosamente").body(service.save(clientRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<ClientResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<ClientResponse> update(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        try {
            return DefaultResponse.<ClientResponse>builder()
                .code(200).status(false).message("Cliente actualizado").body(service.update(clientRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<ClientResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Cliente eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

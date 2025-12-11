package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.BusinessService;
import com.cristianmartinez.api.backendventa.dto.request.BusinessRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    private BusinessService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<BusinessResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<BusinessResponse>> businesses = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<BusinessResponse>>>builder()
                .code(200).status(false).message("Negocios obtenido").body(businesses).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<BusinessResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<BusinessResponse> getById(@PathVariable Long id) {

        try {
            return DefaultResponse.<BusinessResponse>builder()
                .code(200).status(false).message("Negocio encontrado").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<BusinessResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<BusinessResponse> create(@RequestBody BusinessRequest businessRequest) {
        try {
             return DefaultResponse.<BusinessResponse>builder()
                .code(200).status(false).message("Negocio creado exitosamente").body(service.save(businessRequest)).build();
        } catch (Exception e) {
            return DefaultResponse.<BusinessResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @PutMapping("/{id}")
    public DefaultResponse<BusinessResponse> update(@PathVariable Long id, @RequestBody BusinessRequest businessRequest) {
        try {
            return DefaultResponse.<BusinessResponse>builder()
                .code(200).status(false).message("Negocio actualizado").body(service.update(businessRequest, id)).build();
        } catch (Exception e) {
           return DefaultResponse.<BusinessResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Negocio eliminado").body(null).build();
        } catch (Exception e) {
           return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}

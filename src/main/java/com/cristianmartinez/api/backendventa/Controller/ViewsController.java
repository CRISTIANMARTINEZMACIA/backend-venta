package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.cristianmartinez.api.backendventa.Services.ViewsService;
import com.cristianmartinez.api.backendventa.dto.request.ViewRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewResponse;
import com.cristianmartinez.api.backendventa.dto.response.ViewTreeResponse;

import java.util.List;

@RestController
@RequestMapping("/api/views")
public class ViewsController {
    @Autowired
    private ViewsService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<ViewResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,  @RequestParam(defaultValue = "") String name,@RequestParam(required = false) Long father) {
        try {
             Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
        PaginationResponse<List<ViewResponse>> views = service.findAll(pageable, name, father);
        return DefaultResponse.<PaginationResponse<List<ViewResponse>>>builder()
                .code(200).status(false).message("Vistas obtenidas").body(views).build();
        } catch (Exception e) {
            return DefaultResponse.<PaginationResponse<List<ViewResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @GetMapping("/tree")
    public DefaultResponse<List<ViewTreeResponse>> getTree() {
        try {
            List<ViewTreeResponse> views = service.findAllByFather();
        return DefaultResponse.<List<ViewTreeResponse>>builder()
                .code(200).status(false).message("Vistas obtenidas").body(views).build();
        } catch (Exception e) {
          return DefaultResponse.<List<ViewTreeResponse>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @GetMapping("/{id}")
    public DefaultResponse<ViewResponse> getById(@PathVariable Long id) {
        try {
            return DefaultResponse.<ViewResponse>builder()
                .code(200).status(false).message("Vista encontrada").body(service.findById(id)).build();
        } catch (Exception e) {
            return DefaultResponse.<ViewResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }

    @PostMapping
    public DefaultResponse<ViewResponse> create(@RequestBody ViewRequest viewRequest) {
        try {
             return DefaultResponse.<ViewResponse>builder()
                .code(200).status(false).message("Vista creada").body(service.save(viewRequest)).build();
        } catch (Exception e) {
           return DefaultResponse.<ViewResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @PutMapping("/{id}")
    public DefaultResponse<ViewResponse> update(@PathVariable Long id, @RequestBody ViewRequest viewRequest) {
        try {
            return DefaultResponse.<ViewResponse>builder()
                .code(200).status(false).message("Vista actualizada").body(service.update(viewRequest, id)).build();
        } catch (Exception e) {
            return DefaultResponse.<ViewResponse>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
       
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder()
                .code(200).status(false).message("Vista eliminada").body(null).build();
        } catch (Exception e) {
            return DefaultResponse.<Void>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
        
    }
}


package com.cristianmartinez.api.backendventa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.cristianmartinez.api.backendventa.Services.UserService;
import com.cristianmartinez.api.backendventa.dto.request.UserRequest;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public DefaultResponse<PaginationResponse<List<UserResponse>>> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {
         try {
            Pageable pageable = PageRequest.of(page, size,Sort.by("id").descending());
            PaginationResponse<List<UserResponse>> users = service.findAll(pageable);
        return DefaultResponse.<PaginationResponse<List<UserResponse>>>builder()
                .code(200).status(false).message("usuarios obtenidos").body(users).build();
        } catch (Exception e) {
           return DefaultResponse.<PaginationResponse<List<UserResponse>>>builder()
                .code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
        }
    }

    @GetMapping("/{id}")
    public DefaultResponse<UserResponse> getById(@PathVariable Long id) {
       try {
        return DefaultResponse.<UserResponse>builder().code(200).status(false).message("Usuario encontrado").body(service.findById(id)).build();
       } catch (Exception e) {
       return DefaultResponse.<UserResponse>builder().code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
       }
    }

    @PostMapping
    public DefaultResponse<UserResponse> create(@RequestBody UserRequest entity) {
       try {
        return DefaultResponse.<UserResponse>builder().code(200).status(false).message("Usuario creado").body(service.save(entity)).build();
       } catch (Exception e) {
       return DefaultResponse.<UserResponse>builder().code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
       }
    }

    @PutMapping("/{id}")
    public DefaultResponse<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest entity) {
       try {
        return DefaultResponse.<UserResponse>builder().code(200).status(false).message("Usuario actualizado").body(service.update(entity,id)).build();
       } catch (Exception e) {
       return DefaultResponse.<UserResponse>builder().code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
       }
    }

    @DeleteMapping("/{id}")
    public DefaultResponse<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        return DefaultResponse.<Void>builder().code(200).status(false).message("Usuario encontrado").body(null).build();
       } catch (Exception e) {
       return DefaultResponse.<Void>builder().code(e.hashCode()).status(true).message(e.getMessage()).body(null).build();
       }
    }
}
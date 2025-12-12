package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Business;
import com.cristianmartinez.api.backendventa.Entity.Rol;
import com.cristianmartinez.api.backendventa.Entity.User;
import com.cristianmartinez.api.backendventa.Repository.UserRepository;
import com.cristianmartinez.api.backendventa.dto.request.UserRequest;
import com.cristianmartinez.api.backendventa.dto.response.BusinessResponse;
import com.cristianmartinez.api.backendventa.dto.response.PaginationResponse;
import com.cristianmartinez.api.backendventa.dto.response.RolResponse;
import com.cristianmartinez.api.backendventa.dto.response.UserResponse;

import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public PaginationResponse<List<UserResponse>> findAll(Pageable pageable) {

        Page<User> users = repository.findAll(pageable);
        return PaginationResponse.<List<UserResponse>>builder()
                .count(users.getTotalElements())
                .page(users.getTotalPages())
                .content(users.stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public UserResponse findById(Long id) {
        return mapToResponse(repository.findById(id).get());
    }

    public UserResponse save(UserRequest userRequest) {
        return mapToResponse(repository.save(mapToEntity(userRequest)));
    }

    public UserResponse update(UserRequest userRequest, Long id) {
        User entity = repository.findById(id).get();

        entity.setName(userRequest.getName());
        entity.setLastName(userRequest.getLastName());
        entity.setEmail(userRequest.getEmail());
        entity.setBusiness(Business.builder().id(userRequest.getBusiness()).build());
        entity.setRol(Rol.builder().id(userRequest.getRol()).build());

        User saved = repository.save(entity);
        return mapToResponse(saved);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private User mapToEntity(UserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .business(Business.builder().id(userRequest.getBusiness()).build())
                .rol(Rol.builder().id(userRequest.getRol()).build())
                .passwordHash(sha256(userRequest.getPasswordHash()))
                .build();
    }

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return "";
        }

    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .business(mapToResponseBusiness(user.getBusiness()))
                .rol(mapToResponseRole(user.getRol()))
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

    private RolResponse mapToResponseRole(Rol rol) {
        return RolResponse.builder()
                .id(rol.getId())
                .name(rol.getName())
                .description(rol.getDescription())
                .build();
    }
}

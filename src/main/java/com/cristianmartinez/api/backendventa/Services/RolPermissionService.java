package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.RolPermission;
import com.cristianmartinez.api.backendventa.Repository.RolPermissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolPermissionService {
    @Autowired
    private RolPermissionRepository repository;

    public List<RolPermission> findAll() {
        return repository.findAll();
    }

    public Optional<RolPermission> findById(Long id) {
        return repository.findById(id);
    }

    public RolPermission save(RolPermission entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

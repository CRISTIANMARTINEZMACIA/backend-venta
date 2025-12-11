package com.cristianmartinez.api.backendventa.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.ViewPermission;
import com.cristianmartinez.api.backendventa.Repository.ViewPermissionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViewPermissionService {
    @Autowired
    private ViewPermissionRepository repository;

    public List<ViewPermission> findAll() {
        return repository.findAll();
    }

    public Optional<ViewPermission> findById(Long id) {
        return repository.findById(id);
    }

    public ViewPermission save(ViewPermission entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

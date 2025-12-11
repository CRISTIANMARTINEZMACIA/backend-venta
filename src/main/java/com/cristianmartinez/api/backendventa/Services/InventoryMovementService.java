package com.cristianmartinez.api.backendventa.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.InventoryMovement;
import com.cristianmartinez.api.backendventa.Repository.InventoryMovementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryMovementService {
    @Autowired
    private InventoryMovementRepository repository;

    public List<InventoryMovement> findAll() {
        return repository.findAll();
    }

    public Optional<InventoryMovement> findById(Long id) {
        return repository.findById(id);
    }

    public InventoryMovement save(InventoryMovement entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

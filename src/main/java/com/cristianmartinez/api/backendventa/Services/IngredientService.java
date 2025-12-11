package com.cristianmartinez.api.backendventa.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Ingredient;
import com.cristianmartinez.api.backendventa.Repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public Optional<Ingredient> findById(Long id) {
        return repository.findById(id);
    }

    public Ingredient save(Ingredient entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

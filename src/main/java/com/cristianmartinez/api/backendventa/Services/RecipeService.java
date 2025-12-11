package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Recipe;
import com.cristianmartinez.api.backendventa.Repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository repository;

    public List<Recipe> findAll() {
        return repository.findAll();
    }

    public Optional<Recipe> findById(Long id) {
        return repository.findById(id);
    }

    public Recipe save(Recipe entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

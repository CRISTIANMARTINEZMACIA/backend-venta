package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Sale;
import com.cristianmartinez.api.backendventa.Repository.SaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository repository;

    public List<Sale> findAll() {
        return repository.findAll();
    }

    public Optional<Sale> findById(Long id) {
        return repository.findById(id);
    }

    public Sale save(Sale entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

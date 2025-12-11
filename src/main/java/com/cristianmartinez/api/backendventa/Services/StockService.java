package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.Stock;
import com.cristianmartinez.api.backendventa.Repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository repository;

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public Optional<Stock> findById(Long id) {
        return repository.findById(id);
    }

    public Stock save(Stock entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

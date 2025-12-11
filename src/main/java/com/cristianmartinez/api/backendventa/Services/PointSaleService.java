package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.PointSale;
import com.cristianmartinez.api.backendventa.Repository.PointSaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PointSaleService {
    @Autowired
    private PointSaleRepository repository;

    public List<PointSale> findAll() {
        return repository.findAll();
    }

    public Optional<PointSale> findById(Long id) {
        return repository.findById(id);
    }

    public PointSale save(PointSale entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

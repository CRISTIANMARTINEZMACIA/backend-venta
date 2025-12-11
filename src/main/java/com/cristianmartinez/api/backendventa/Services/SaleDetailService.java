package com.cristianmartinez.api.backendventa.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianmartinez.api.backendventa.Entity.SaleDetail;
import com.cristianmartinez.api.backendventa.Repository.SaleDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository repository;

    public List<SaleDetail> findAll() {
        return repository.findAll();
    }

    public Optional<SaleDetail> findById(Long id) {
        return repository.findById(id);
    }

    public SaleDetail save(SaleDetail entity) {
        return repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
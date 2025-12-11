package com.cristianmartinez.api.backendventa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianmartinez.api.backendventa.Entity.PointSale;

@Repository
public interface PointSaleRepository extends JpaRepository<PointSale, Long> {
}
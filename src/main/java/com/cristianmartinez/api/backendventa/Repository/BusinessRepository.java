package com.cristianmartinez.api.backendventa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristianmartinez.api.backendventa.Entity.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
}

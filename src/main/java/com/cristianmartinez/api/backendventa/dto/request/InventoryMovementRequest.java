package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cristianmartinez.api.utils.TipoMovimiento;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementRequest {
    private Long stock;
    private Long user;
    private BigDecimal amount;
    private TipoMovimiento typeMovement;
    private LocalDateTime dateMovement;
}

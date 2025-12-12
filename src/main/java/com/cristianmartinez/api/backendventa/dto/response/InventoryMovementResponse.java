package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

enum TipoMovimiento {
    ENTRADA, SALIDA, AJUSTE
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementResponse {
    private Long id;
    private StockResponse stock;
    private String typeMovement;
    private BigDecimal amount;
    private LocalDateTime dateMovement;
    private UserResponse user;
}

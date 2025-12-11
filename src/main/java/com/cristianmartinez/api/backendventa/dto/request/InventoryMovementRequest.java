package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementRequest {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String type;
    private LocalDateTime movementDate;
    private String note;
}

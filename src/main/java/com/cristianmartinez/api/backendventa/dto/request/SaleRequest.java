package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cristianmartinez.api.utils.EstadoVenta;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    private Long client;
    private Long pointSale;
    private Long business;
    private Long user;
    private BigDecimal total;
    private String typePayment;
    private EstadoVenta status;
    private LocalDateTime dateSale;
}

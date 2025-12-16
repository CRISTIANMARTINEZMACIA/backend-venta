package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponse {
    private Long id;
    private PointSaleResponse pointSale;
    private UserResponse user;
    private ClientResponse client;
    private LocalDateTime dateSale;
    private BigDecimal total;
    private String typePayment;
    private String status;
}

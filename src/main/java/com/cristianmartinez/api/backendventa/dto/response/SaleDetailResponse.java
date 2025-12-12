package com.cristianmartinez.api.backendventa.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponse {
    private Long id;
    private SaleResponse sale;
    private ProductResponse product;
    private BigDecimal amount;
    private BigDecimal priceUnitary;
    private BigDecimal subtotal;
}

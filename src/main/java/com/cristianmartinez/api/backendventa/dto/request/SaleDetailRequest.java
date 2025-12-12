package com.cristianmartinez.api.backendventa.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailRequest {
    private Long sale;
    private Long product;
    private BigDecimal amount;
    private BigDecimal priceUnitary;
    private BigDecimal subtotal;
}

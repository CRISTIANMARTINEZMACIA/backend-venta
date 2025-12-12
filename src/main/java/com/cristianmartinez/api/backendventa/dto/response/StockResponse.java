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
public class StockResponse {
    private Long id;
    private ProductResponse product;
    private BigDecimal amountCurrent;
    private String unitMeasure;
    private BigDecimal stockMin;
}

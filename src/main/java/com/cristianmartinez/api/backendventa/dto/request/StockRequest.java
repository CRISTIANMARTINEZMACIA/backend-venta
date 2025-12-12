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
public class StockRequest {
    private Long product;
    private BigDecimal amountCurrent;
    private String unitMeasure;
    private BigDecimal stockMin;
}

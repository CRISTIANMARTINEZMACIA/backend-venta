package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailRequest {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private Double price;
}

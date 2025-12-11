package com.cristianmartinez.api.backendventa.dto.response;

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
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private Double price;
}

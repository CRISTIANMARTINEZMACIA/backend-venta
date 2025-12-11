package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    private Long id;
    private Long clientId;
    private Long pointSaleId;
    private Double total;
    private LocalDateTime saleDate;
    private List<SaleDetailRequest> details;
}

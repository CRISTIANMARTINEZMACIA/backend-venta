package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSummaryResponse {
    private LocalDateTime from;
    private LocalDateTime to;
    private Long pointSaleId;
    private Double totalSales;
    private Integer totalItems;
}

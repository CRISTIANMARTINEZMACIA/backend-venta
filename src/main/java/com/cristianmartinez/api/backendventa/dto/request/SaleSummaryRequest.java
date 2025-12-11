package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSummaryRequest {
    private LocalDateTime from;
    private LocalDateTime to;
    private Long pointSaleId;
}

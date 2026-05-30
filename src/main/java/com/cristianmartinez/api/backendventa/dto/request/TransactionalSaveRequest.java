package com.cristianmartinez.api.backendventa.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionalSaveRequest {
   private SaleRequest sale;
    private List<SaleDetailRequest> saleDetails;
}

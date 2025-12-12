package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewPermissionRequest {
    private Long view;
    private Long permission;
    private Boolean read;
    private Boolean write;
    private Boolean update;
    private Boolean delete;
    private Boolean print;
    private Boolean lead;
}

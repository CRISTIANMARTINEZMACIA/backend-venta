package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewPermissionResponse {
    private Long id;
    private ViewResponse view;
    private PermissionResponse permission;
    private Boolean read;
    private Boolean write;
    private Boolean update;
    private Boolean delete;
    private Boolean print;
    private Boolean lead;
}

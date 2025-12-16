package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermissionResponse {
    private Long id;
    private RolResponse rol;
    private ViewPermissionResponse viewPermission;
    private Boolean read;
    private Boolean write;
    private Boolean update;
    private Boolean delete;
    private Boolean print;
    private Boolean lead;
}

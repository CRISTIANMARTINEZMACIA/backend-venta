package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewResponse {
    private Long id;
    private String name;
    private String route;
    private String icon;
    private Boolean active;
    private ViewResponse father;
}

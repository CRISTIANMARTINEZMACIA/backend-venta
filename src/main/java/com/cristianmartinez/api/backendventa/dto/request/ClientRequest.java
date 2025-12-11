package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
}

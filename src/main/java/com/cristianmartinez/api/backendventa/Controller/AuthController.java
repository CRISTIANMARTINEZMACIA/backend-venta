package com.cristianmartinez.api.backendventa.Controller;

import com.cristianmartinez.api.backendventa.Services.JwtService;
import com.cristianmartinez.api.backendventa.Services.UserDetailsServiceImpl;
import com.cristianmartinez.api.backendventa.dto.request.AuthRequest;

import com.cristianmartinez.api.backendventa.dto.response.AuthResponse;
import com.cristianmartinez.api.backendventa.dto.response.DefaultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public DefaultResponse<AuthResponse> create(@RequestBody AuthRequest authRequest) {
        try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
           );

        } catch (AuthenticationException e) {
            return DefaultResponse.<AuthResponse>builder()
                    .code(401).status(true).message(e.getMessage()).body(null).build();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwtToken = jwtService.generateToken(userDetails);
        return  DefaultResponse.<AuthResponse>builder()
                .code(200).status(false).message("Autenticado correctamente").body(AuthResponse.builder().jwtToken(jwtToken).build()).build();
    }
}

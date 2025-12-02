package br.fai.lds.backendspringlds.controller;

import br.fai.lds.backendspringlds.domain.dto.AuthenticationDto;
import br.fai.lds.backendspringlds.domain.dto.JwtTokenDto;
import br.fai.lds.backendspringlds.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    public AuthenticationRestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDto> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        final String token = authenticationService.authenticate(authenticationDto.getEmail(), authenticationDto.getPassword());
        return ResponseEntity.ok(new JwtTokenDto(token));
    }
}

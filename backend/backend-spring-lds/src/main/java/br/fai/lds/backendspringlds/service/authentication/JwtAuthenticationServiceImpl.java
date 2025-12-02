package br.fai.lds.backendspringlds.service.authentication;

import br.fai.lds.backendspringlds.service.authentication.jwt.JwtService;
import br.fai.lds.domain.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UserModel userModel = (UserModel) userDetails;

        // O UserModel do projeto do usuário tem o campo 'tipo' que corresponde ao 'role' do projeto de referência.
        // O campo 'nome' corresponde ao 'fullname'.
        return jwtService.generateToken(
                userDetails,
                userModel.getNome(),
                UserModel.Tipo.valueOf(userModel.getTipo()), // Assumindo que o enum Tipo tem os mesmos nomes de role
                userModel.getEmail()
        );
    }
}

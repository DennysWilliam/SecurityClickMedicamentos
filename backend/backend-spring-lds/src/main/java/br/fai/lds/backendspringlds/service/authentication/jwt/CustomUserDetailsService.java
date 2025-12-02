package br.fai.lds.backendspringlds.service.authentication.jwt;

import br.fai.lds.domain.UserModel;
import br.fai.lds.backend.usecases.port.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // NÃO EXISTE readByEmail no seu repository
        // Então precisamos criar uma consulta manual:
        UserModel user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }

        return user; // UserModel implementa UserDetails
    }
}

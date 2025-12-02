package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backend.usecases.authentication.LoginUseCase;
import br.fai.lds.backend.usecases.authentication.LogoutUseCase;
import br.fai.lds.domain.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationRestController {
    private final LoginUseCase loginUseCase;

    private final LogoutUseCase logoutUseCase;

    public AuthenticationRestController(LoginUseCase loginUseCase, LogoutUseCase logoutUseCase) {
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/login")
    public UserModel authenticateV1(@RequestBody UserModel userModel) {
        String email = userModel.getEmail();
        String password = userModel.getSenha();
        UserModel user = loginUseCase.login(email, password);
        return user;
    }

    @GetMapping("/logged")
    public UserModel getLoggedUser(){
        UserModel user = loginUseCase.find();
        return user;
    }

    @DeleteMapping("/logout")
    public boolean logout(){
        return logoutUseCase.logout();
    }

}

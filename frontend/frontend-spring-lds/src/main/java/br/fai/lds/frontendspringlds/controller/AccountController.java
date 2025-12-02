package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.account.AccountuseCase;
import br.fai.lds.frontend.usecases.account.LoggedUserUseCase;
import br.fai.lds.frontend.usecases.account.LogoutUseCase;
import br.fai.lds.frontend.usecases.user.FindUserById;
import br.fai.lds.frontend.usecases.user.UpdateUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/account")
public class    AccountController {

    private final FindUserById findUserById;
    private final LoggedUserUseCase loggedUserUseCase;
    private final AccountuseCase accountUseCase;
    private final LogoutUseCase logoutUseCase;

    private final UpdateUser updateUser;

    public AccountController(FindUserById findUserById, LoggedUserUseCase loggedUserUseCase, AccountuseCase accountUseCase, LogoutUseCase logoutUseCase, UpdateUser updateUser) {
        this.findUserById = findUserById;
        this.loggedUserUseCase = loggedUserUseCase;
        this.accountUseCase = accountUseCase;
        this.logoutUseCase = logoutUseCase;
        this.updateUser = updateUser;
    }

    @GetMapping("/login")
    public String getLoginPage(final UserModel userModel, Model model){
        UserModel user = loggedUserUseCase.showLoggedUser();

        if (user == null) {
            return "/account/login";
        }


        return "redirect:/account/profile/" + user.getId();
    }
    @GetMapping("/login/error")
    public String getLoginPageError(final UserModel userModel, Model model){
        String message = "Usuário não encontrado.";
        model.addAttribute("msg", message);
        return "/account/login";
    }
    @GetMapping("/login/createsuccess")
    public String getLoginCreated(final UserModel userModel, Model model){
        String message = "Usuário criado com sucesso!";
        model.addAttribute("msgs", message);
        return "/account/login";
    }
    @GetMapping("/login/errorpassword")
    public String getLoginPageErrorPassword(final UserModel userModel, Model model){
        String message = "As senhas não conferem.";
        model.addAttribute("msg2", message);
        return "/account/login";
    }
    @GetMapping("/login/errorexistsuser")
    public String getLoginPageErrorUserExists(final UserModel userModel, Model model){
        String message = "Nome do usuário ou email já existem.";
        model.addAttribute("msg4", message);
        return "/account/login";
    }
    @GetMapping("/login/passwordlengh")
    public String getLoginPagePasswordLengh(final UserModel userModel, Model model){
        String message = "A senha deve ser maior que 3 digitos";
        model.addAttribute("msg3", message);
        return "/account/login";
    }

    @PostMapping("/authentication")
    public String authentication(@RequestParam("email")String email,
                                 @RequestParam("password")String password,
    final Model model) {

        UserModel user = accountUseCase.login(email, password);


        if (user == null){
            return "redirect:/account/login/error";
        }

        return "redirect:/account/profile/" + user.getId();
    }

    @GetMapping("/profile/{id}")

    public String getProfilePage(@PathVariable("id") final int id, final Model model){
        UserModel user2 = loggedUserUseCase.showLoggedUser();
        final UserModel user = findUserById.findById(id);
        if(user == null){
            return "redirect:/account/login";
        }
        if(user.getId() != user2.getId()){
            return "redirect:/account/login";
        }

        model.addAttribute("user", user);
        return "/account/profile";
    }

    @GetMapping("/logout")
    public String logout(){
        final boolean logout = logoutUseCase.logout();
        return "redirect:/account/login";
    }

    @PostMapping("/balance")
    public String addBalance(final UserModel userModel, final Model model) {
//        final UserModel user = findUserById.findById(userModel.getId());
        updateUser.updateUserBalance(userModel);

        final UserModel user = findUserById.findById(userModel.getId());
        //adiciona o saldo aqui

        model.addAttribute("user", user);
        return "redirect:/account/profile/"+ userModel.getId();
    }
}

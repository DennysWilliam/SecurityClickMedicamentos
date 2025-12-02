package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.pharmacy.UpdatePharmacy;
import br.fai.lds.frontend.usecases.user.CreateUser;
import br.fai.lds.frontend.usecases.user.FindUserById;
import br.fai.lds.frontend.usecases.user.ShowAllUsers;
import br.fai.lds.frontend.usecases.user.UpdateUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final FindUserById findUserById;
    private final ShowAllUsers showAllUsers;
    private final CreateUser createUser;
    private final UpdateUser updateUser;

    public UserController(FindUserById findUserById, ShowAllUsers showAllUsers, CreateUser createUser, UpdatePharmacy updatePharmacy, UpdateUser updateUser) {
        this.findUserById = findUserById;
        this.showAllUsers = showAllUsers;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/all")
    public String getAllUsers(final Model model) {
        List<UserModel> user = showAllUsers.showAllUsers();

        if (user == null)
            user = new ArrayList<>();

        model.addAttribute("users", user);
        return "/user/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") final int id, final Model model){
        final UserModel userModel = findUserById.findById(id);
        model.addAttribute("user", userModel);
        return "/user/edit";
    }
    @GetMapping("/editerror/{id}")
    public String getEditPagePassword(@PathVariable("id") final int id, final Model model){
        final UserModel userModel = findUserById.findById(id);
        final String message = "A senha deve conter mais de 3 digitos.";
        model.addAttribute("msg", message);
        model.addAttribute("user", userModel);
        return "/user/edit";
    }

    @PostMapping("/update")
    public String getUpdatedPharmacy(final UserModel userModel) {
        if(userModel.getSenha().length() < 4){
            return "redirect:/user/editerror/" + userModel.getId();
        }
        final boolean updatedPharmacy = updateUser.updateUser(userModel);
        return "redirect:/account/profile/" + userModel.getId();
    }

    @PostMapping("/add")
    public String createUser(final UserModel userModel){
        if(userModel.getSenha().length() < 4){
            return "redirect:/account/login/passwordlengh";
        }
        if(userModel.getSenha().equals(userModel.getSenhaConfirmacao())){
            final int userId = createUser.createUser(userModel);
            if(userId <= 0){
                return "redirect:/account/login/errorexistsuser";
            }
            return "redirect:/account/login/createsuccess";
        }

//        return "redirect:/account/profile/" + userId;
        return "redirect:/account/login/errorpassword";
    }
}

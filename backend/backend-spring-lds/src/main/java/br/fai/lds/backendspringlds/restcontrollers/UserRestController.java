package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backendspringlds.configuration.UserBackendConfiguration;
import br.fai.lds.domain.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserBackendConfiguration userBackendConfiguration = new UserBackendConfiguration();


    @GetMapping("/all")
    public List<UserModel> getUsers(){
        List<UserModel> user = userBackendConfiguration.findUser().find();

        return user;

    }

    @GetMapping("/find/{id}")
    public UserModel getUserById(@PathVariable("id") final int id){
        UserModel userModel = userBackendConfiguration.findUser().find(id);
        return userModel;
    }

    @Operation(description="Adicionar usuario")
    @PostMapping
    public boolean createUser(@RequestBody final UserModel userModel){
        return userBackendConfiguration.createUser().createUser(userModel);
    }

    @PutMapping("/update/{id}")
    public boolean updateUser(@PathVariable("id") int id, @RequestBody UserModel userModel){
        userModel.setId(id);
        System.out.println(userModel.getCpf());
        return userBackendConfiguration.updateUser().update(userModel);
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") final int id) {
        return userBackendConfiguration.deleteUser().delete(id);
    }


}

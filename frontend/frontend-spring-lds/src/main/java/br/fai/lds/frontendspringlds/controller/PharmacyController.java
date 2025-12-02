package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.account.LoggedUserUseCase;
import br.fai.lds.frontend.usecases.pharmacy.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final ShowAllPharmacys showAllPharmacys;
    private final FindPharmacyById  findPharmacyById;
    private final UpdatePharmacy updatePharmacy;
    private final CreatePharmacy createPharmacy;
    private final DeletePharmacy deletePharmacy;
    private final LoggedUserUseCase loggedUserUseCase;



    public PharmacyController(final ShowAllPharmacys showAllPharmacys, final FindPharmacyById findPharmacyById, UpdatePharmacy updatePharmacy, CreatePharmacy createPharmacy, DeletePharmacy deletePharmacy, LoggedUserUseCase loggedUserUseCase) {
        this.showAllPharmacys = showAllPharmacys;
        this.findPharmacyById = findPharmacyById;
        this.updatePharmacy = updatePharmacy;
        this.createPharmacy = createPharmacy;
        this.deletePharmacy = deletePharmacy;
        this.loggedUserUseCase = loggedUserUseCase;
    }

    @GetMapping("/all")
    public String getAllPharmacys(final Model model) {
        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacys();

        if(pharmacy == null)
            pharmacy = new ArrayList<>();

        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/list";
    }
    @PostMapping("/allByLoc")
    public String getAllPharmacysByLoc(final Model model, final PharmacyModel pharmacyModel) {
        UserModel user = loggedUserUseCase.showLoggedUser();

        if (user == null) {
            return "redirect:/account/login";
        }
        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacysByCity(pharmacyModel);

        if(pharmacy == null)

            return "redirect:/fail";

        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/rent";
    }
    @GetMapping("/allbyid")
    public String getAllPharmacysById(final Model model) {

        UserModel user = loggedUserUseCase.showLoggedUser();
        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacysById(user.getId());

        if(pharmacy == null)
            pharmacy = new ArrayList<>();

        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/list";
    }
    @GetMapping("/allbyidFail")
    public String getAllPharmacysByIdFail(final Model model) {

        UserModel user = loggedUserUseCase.showLoggedUser();
        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacysById(user.getId());

        if(pharmacy == null)
            pharmacy = new ArrayList<>();
        final String error = "Não foi possível editar, a farmacia inserida ja está cadastrada";
        model.addAttribute("errors", error);
        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/list";
    }

    @GetMapping("/allRent")
    public String getAllPharmacysTwo(final Model model) {
        UserModel user = loggedUserUseCase.showLoggedUser();

        if (user == null) {
            return "redirect:/account/login";
        }

        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacys();

        if(pharmacy == null)
            pharmacy = new ArrayList<>();

        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/rent";
    }
    @GetMapping("/allRentFail")
    public String getAllPharmacysTwoFail(final Model model) {
        UserModel user = loggedUserUseCase.showLoggedUser();

        if (user == null) {
            return "redirect:/account/login";
        }

        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacys();

        if(pharmacy == null)
            pharmacy = new ArrayList<>();

        final String message = "Você não possui saldo suficiente.";
        model.addAttribute("msg", message);
        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/rent";
    }
    @GetMapping("/allRentSuccess")
    public String getAllPharmacysTwoSuccess(final Model model) {
        UserModel user = loggedUserUseCase.showLoggedUser();

        if (user == null) {
            return "redirect:/account/login";
        }

        List<PharmacyModel> pharmacy = showAllPharmacys.showAllPharmacys();

        if(pharmacy == null)
            pharmacy = new ArrayList<>();

        final String message = "Seu medicamento foi reservado com sucesso.";
        model.addAttribute("msg10", message);
        model.addAttribute("pharmacys", pharmacy);
        return "/pharmacy/rent";
    }


    @PostMapping("/update")
    public String getUpdatedPharmacy(final PharmacyModel pharmacyModel) {
        final boolean updatedPharmacy = updatePharmacy.updatePharmacy(pharmacyModel);
        if(updatedPharmacy == true){
            return "redirect:/pharmacy/allbyidFail";
        }
        return "redirect:/pharmacy/allbyid";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") final int id, final Model model){
        final PharmacyModel pharmacyModel = findPharmacyById.findById(id);
        model.addAttribute("pharmacy", pharmacyModel);
        return "/pharmacy/edit";
    }

    @GetMapping("/create")
    public String creatingPharmacy(final PharmacyModel pharmacyModel)    {
        return "/pharmacy/create";
    }
    @GetMapping("/createerrorexists")
    public String creatingPharmacyError(final PharmacyModel pharmacyModel, final Model model)    {
        String message = "Esta farmacia ja esta cadastrada";
        model.addAttribute("msg", message);
        return "/pharmacy/create";
    }

    @PostMapping("/add")
    public String addPharmacy(final PharmacyDto pharmacyDto){
        UserModel user = loggedUserUseCase.showLoggedUser();
        PharmacyModel pharmacyModel = pharmacyDto.toPharmacyModel();
        pharmacyModel.setId(user.getId());
        final int createdPharmacy = createPharmacy.createPharmacy(pharmacyModel);
        if(createdPharmacy <= 0){
            return "redirect:/pharmacy/createerrorexists";
        }
        return "redirect:/pharmacy/allbyid";
    }

    @GetMapping("/delete/{id}")
    public String deletePharmacy(@PathVariable("id") final int id){
        final boolean deletedPharmacy = deletePharmacy.deletePharmacy(id);
        return "redirect:/pharmacy/allbyid";
    }

    @GetMapping("/rent")
    public String getRentPage(){
        return "/pharmacy/rent";
    }

    public class PharmacyDto {
        private int id;
        private String fantasyName;
        private String cnpj;
        private String publicPlace;
        private String cep;
        private String street;
        private String city;
        private String state;
        private String number;
        private int numberVacancies;

        private String medicationPrice;

        public PharmacyModel toPharmacyModel() {
            PharmacyModel entity = new PharmacyModel();
            entity.setCep(cep);
            entity.setCnpj(cnpj);
            entity.setId(id);

            return entity;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFantasyName() {
            return fantasyName;
        }

        public void setFantasyName(String fantasyName) {
            this.fantasyName = fantasyName;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public String getPublicPlace() {
            return publicPlace;
        }

        public void setPublicPlace(String publicPlace) {
            this.publicPlace = publicPlace;
        }

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}



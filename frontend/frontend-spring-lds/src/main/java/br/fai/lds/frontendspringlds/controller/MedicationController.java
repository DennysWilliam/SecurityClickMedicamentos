package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.UserModel;
import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.account.LoggedUserUseCase;
import br.fai.lds.frontend.usecases.pharmacy.FindPharmacyById;
import br.fai.lds.frontend.usecases.user.FindUserById;
import br.fai.lds.frontend.usecases.user.UpdateUser;
import br.fai.lds.frontend.usecases.medication.ShowMedicationById;
import br.fai.lds.frontend.usecases.medication.UpdateMedication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/medication")
public class MedicationController {
    private final ShowMedicationById showMedicationById;
    private final UpdateMedication updateMedication;
    private final FindPharmacyById findPharmacyById;
    private final LoggedUserUseCase loggedUserUseCase;
    private final FindUserById findUserById;
    private final UpdateUser updateUser;

    public MedicationController( UpdateMedication updateMedication, ShowMedicationById showMedicationById, FindPharmacyById findPharmacyById, LoggedUserUseCase loggedUserUseCase, FindUserById findUserById, UpdateUser updateUser) {

        this.showMedicationById = showMedicationById;
        this.updateMedication = updateMedication;
        this.findPharmacyById = findPharmacyById;
        this.loggedUserUseCase = loggedUserUseCase;
        this.findUserById = findUserById;
        this.updateUser = updateUser;
    }

    @GetMapping("/list/pharmacy/{id}")
    public String getListPage(@PathVariable("id") final int id, final Model model){


        return "/medication/list";
    }

    @GetMapping("/rent/{id}")
    public String getRentPage(@PathVariable("id") final int id, final Model model){

        MedicationModel medicationModel = showMedicationById.showMedicationById(id);
        model.addAttribute("medication", medicationModel);
        final PharmacyModel pharmacyModel = findPharmacyById.findById(medicationModel.getFarmaciaId());
        model.addAttribute("pharmacy", pharmacyModel);
        return "/medication/rent";
    }

    @PostMapping("/update/{id}")
    public String getUpdatedMedication(@PathVariable("id") final int id, final MedicationModel medicationModel) {
        UserModel user2 = loggedUserUseCase.showLoggedUser();
        UserModel userToBeUpdated = findUserById.findById(user2.getId());
        final boolean response = updateMedication.updatePharmacy(id, medicationModel);
        return "redirect:/pharmacy/allRentSuccess";
    }
}

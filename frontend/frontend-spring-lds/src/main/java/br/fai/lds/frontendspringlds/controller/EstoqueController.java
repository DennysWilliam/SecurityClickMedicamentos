package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.EstoqueModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/estoques")
public class EstoqueController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080/api/estoques";

    @GetMapping
    public String list(Model model) {
        List<EstoqueModel> estoques = Arrays.asList(restTemplate.getForObject(baseUrl, EstoqueModel[].class));
        model.addAttribute("estoques", estoques);
        return "estoque/list";
    }

}

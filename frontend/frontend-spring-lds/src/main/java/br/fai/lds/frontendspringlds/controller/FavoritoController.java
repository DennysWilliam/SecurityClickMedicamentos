package br.fai.lds.frontendspringlds.controller;

import br.fai.lds.domain.FavoritoModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/favoritos")
public class FavoritoController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080/api/favoritos";

    @GetMapping
    public String list(Model model) {
        List<FavoritoModel> favoritos = Arrays.asList(restTemplate.getForObject(baseUrl, FavoritoModel[].class));
        model.addAttribute("favoritos", favoritos);
        return "favorito/list";
    }
}

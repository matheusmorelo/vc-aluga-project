package com.application.vocealuga.controller;

import com.application.vocealuga.dto.AgenciaDto;
import com.application.vocealuga.service.impl.AgenciaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class AgenciaController {
    private AgenciaServiceImpl agenciaService;

    public AgenciaController(AgenciaServiceImpl agenciaService) {
        this.agenciaService = agenciaService;
    }

    @GetMapping("/agencia")
    String agencia(Model model) {
        model.addAttribute("agencia", new AgenciaDto());
        return "agencia";
    }

    @PostMapping("/criarAgencia")
    public String createAgencia(@ModelAttribute AgenciaDto agenciaDto) {
        Boolean agenciaExists = agenciaService.existsByContato(agenciaDto.getContato());
        if (agenciaExists) {
            return "redirect:/agencia?error=1";
        }

        Boolean agenciaSaved = agenciaService.saveAgencia(agenciaDto);
        if (!agenciaSaved) {
            return "redirect:/agencia?error=2";
        }

        return "redirect:/agencia?success";
    }
}

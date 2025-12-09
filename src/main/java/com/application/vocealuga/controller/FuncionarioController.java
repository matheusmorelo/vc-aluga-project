package com.application.vocealuga.controller;

import com.application.vocealuga.dto.FuncionarioDto;
import com.application.vocealuga.service.impl.FuncionarioServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class FuncionarioController {
    private FuncionarioServiceImpl funcionarioService;

    public FuncionarioController(FuncionarioServiceImpl funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/cadastroFuncionario")
    public String functionario(Model model) {
        model.addAttribute("funcionario", new FuncionarioDto());
        return "cadastrar-funcionario";
    }

    @PostMapping("/cadastrarFuncionario")
    public String cadastrarFuncionario(@ModelAttribute FuncionarioDto funcionarioDto) {
        try {
            funcionarioService.createFuncionario(funcionarioDto);
            return "redirect:/cadastroFuncionario?success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/cadastroFuncionario?error";
        }
    }
}

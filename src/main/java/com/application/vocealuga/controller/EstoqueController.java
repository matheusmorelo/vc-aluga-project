package com.application.vocealuga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class EstoqueController {

    @GetMapping("/estoque")
    public String estoque() {
        return "estoque";
    }

    @PostMapping("/criarEstoque")
    public String criarEstoque() {
        return "estoque";
    }
}

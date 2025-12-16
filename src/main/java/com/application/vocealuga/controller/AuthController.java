package com.application.vocealuga.controller;

import com.application.vocealuga.dto.RegistrationDto;
import com.application.vocealuga.entity.Permissao;
import com.application.vocealuga.repository.PermissaoRepository;
import com.application.vocealuga.service.impl.ClienteServiceImpl;
import com.application.vocealuga.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.security.auth.login.LoginException;
import java.util.List;

@Controller
@EnableWebMvc
public class AuthController {
    private PermissaoRepository permissaoRepository;
    private final ClienteServiceImpl clienteService;
    private final FuncionarioService funcionarioService;

    public AuthController(PermissaoRepository permissaoRepository, ClienteServiceImpl clienteService, FuncionarioService funcionarioService) {
        this.permissaoRepository = permissaoRepository;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/login")
    String login(Model model) { return "login"; }

    @PostMapping(path = "/login", consumes = "application/x-www-form-urlencoded") 
    public String login(@RequestParam("username") String documento, 
                        @RequestParam("password") String senha) {    
        try {
            funcionarioService.login(documento, senha);
                        return "redirect:/home"; 
            
        } catch (LoginException e) {
            return "redirect:/login?error"; 
        }
    }

    @GetMapping("/cadastro")
    public String register(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        List<Permissao> permissoes = permissaoRepository.findAll();

        model.addAttribute("cliente", registrationDto);
        model.addAttribute("permissoes", permissoes);
        return "cadastro";
    }

    @PostMapping(path = "/cadastrar", consumes = "application/x-www-form-urlencoded")
    public String createCliente(@ModelAttribute RegistrationDto registrationDto, BindingResult result) {
        String fieldError = null;
        if (registrationDto.getClienteType() == 1) {
            if (clienteService.existsByCpf(registrationDto.getCpf())) {
                fieldError = "cpf";
                result.rejectValue(fieldError, "error.cpf", "CPF já cadastrado");
            }
        } else if (registrationDto.getClienteType() == 2) {
            if (clienteService.existsByCnpj(registrationDto.getCnpj())) {
                fieldError = "cnpj";
                result.rejectValue(fieldError, "error.cnpj", "CNPJ já cadastrado");
            }
        } else {
            fieldError = "clienteType";
            result.rejectValue(fieldError, "error.clienteType", "Tipo de cliente inválido");
        }

        if (result.hasErrors()) {
            return "redirect:/cadastro?error";
        }

        if (!clienteService.saveCliente(registrationDto)) {
            return "redirect:/cadastro?error";
        }

        return "redirect:/cadastro?success";
    }
}
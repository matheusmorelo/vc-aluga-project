package com.application.vocealuga.controller;

import com.application.vocealuga.dto.ClienteDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.service.impl.ClienteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ClienteController {
    private ClienteServiceImpl clienteService;

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/consultarCliente")
    String consultarCliente(@ModelAttribute("clienteDto") ClienteDto clienteDto, @RequestParam(name = "document", required = false) String document, Model model) {
            if (clienteDto.getDocument() != null) {
                String documentFromDto = clienteDto.getDocument();
                ClienteEntity cliente = getClienteFromRequest(documentFromDto);
                if (cliente == null) return "redirect:/consultarCliente?error=1";
                model.addAttribute("cliente", cliente);
            } else if (document != null) {
                ClienteEntity cliente = getClienteFromRequest(document);
                if (cliente == null) return "redirect:/consultarCliente?error=1";
                model.addAttribute("cliente", cliente);
            } else {
                model.addAttribute("clientes", clienteService.findAll());
            }

            model.addAttribute("clienteDto", new ClienteDto());
            return "consultarCliente";
    }

    private ClienteEntity getClienteFromRequest(String document) {
        ClienteEntity cliente;
        if (document.length() == 11) {
            cliente = clienteService.findByCpf(document);
        } else if (document.length() == 14) {
            cliente = clienteService.findByCnpj(document);
        } else {
            return null;
        }

        return cliente;
    }
}

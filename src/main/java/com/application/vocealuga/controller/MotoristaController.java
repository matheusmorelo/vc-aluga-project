package com.application.vocealuga.controller;

import com.application.vocealuga.dto.MotoristaDto;
import com.application.vocealuga.dto.VeiculoSearchDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Motorista;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.MotoristaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class MotoristaController {
    private final MotoristaRepository motoristaRepository;
    private final ClienteRepository clienteRepository;

    public MotoristaController(MotoristaRepository motoristaRepository, ClienteRepository clienteRepository) {
        this.motoristaRepository = motoristaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/cadastroMotorista")
    public String cadastroMotorista(Model model) {
        model.addAttribute("motorista", new MotoristaDto());
        return "form-motorista";
    }

    @PostMapping("/cadastrarMotorista")
    public String cadastroMotorista(@ModelAttribute MotoristaDto motoristaDto) {
        Motorista motorista = motoristaRepository.findByCnh(motoristaDto.getCnh());
        if (motorista != null) {
            return "redirect:/cadastroMotorista?error=1";
        }

        Motorista newDriver = new Motorista();
        newDriver.setCnh(motoristaDto.getCnh());
        newDriver.setNome(motoristaDto.getNome());
        ClienteEntity cliente = clienteRepository.findByCpf(motoristaDto.getDocumento());
        newDriver.setCliente(cliente);

        try {
            motoristaRepository.save(newDriver);
            return "redirect:/cadastroMotorista?success";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/cadastroMotorista?error=2";
        }
    }

    @GetMapping("/motorista/verificar")
    public String verificarMotorista(@RequestParam(name = "cnh", required = false) String cnh, Model model) {
        if (cnh != null && !cnh.isEmpty()) {
            Motorista motorista = motoristaRepository.findByCnh(cnh);
            if (motorista == null) {
                return "redirect:/motorista/verificar?error=1";
            }
            model.addAttribute("motorista", motorista);
        } else {
            model.addAttribute("motoristas", motoristaRepository.findAll());
        }

        return "verificar-motorista";
    }
}

package com.application.vocealuga.controller;

import com.application.vocealuga.dto.ReservaDto;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.service.ReservaService;
import com.application.vocealuga.service.impl.ReservaServiceImpl;
import com.application.vocealuga.service.impl.VeiculoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class ReservaController {
    private VeiculoServiceImpl veiculoService;
    private ReservaServiceImpl reservaService;

    public ReservaController(VeiculoServiceImpl veiculoService, ReservaServiceImpl reservaService) {
        this.veiculoService = veiculoService;
        this.reservaService = reservaService;
    }

    @GetMapping("/reserva/veiculo/{id}")
    public String reserva(@PathVariable("id") Long id, Model model) {
        Veiculo veiculo = veiculoService.findById(id);

        if (veiculo == null) {
            return "redirect:/catalogoVeiculos";
        }

        model.addAttribute("reserva", new ReservaDto());
        model.addAttribute("veiculo", veiculo);
        return "reserva";
    }

    @PostMapping("/reservar/veiculo/{id}")
    public String reservar(@PathVariable("id") Long id, @ModelAttribute ReservaDto reservaDto) {
        try {
            reservaService.saveReserva(reservaDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/reserva/veiculo/" + id + "?error";
        }

        return "redirect:/pagamento";
    }
}

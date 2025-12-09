package com.application.vocealuga.controller;

import com.application.vocealuga.dto.DevolucaoDto;
import com.application.vocealuga.dto.VeiculoSearchDto;
import com.application.vocealuga.dto.VeiculoDto;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.service.impl.AgenciaServiceImpl;
import com.application.vocealuga.service.impl.VeiculoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class VeiculoController {
    private VeiculoServiceImpl veiculoService;
    private AgenciaServiceImpl agenciaService;

    public VeiculoController(VeiculoServiceImpl veiculoService, AgenciaServiceImpl agenciaService) {
        this.veiculoService = veiculoService;
        this.agenciaService = agenciaService;
    }

    @GetMapping("/catalogoVeiculos")
    public String catalogoVeiculos(Model model) {
        model.addAttribute("veiculos", veiculoService.findBySituacao("ativo"));
        return "catalogo-veiculos";
    }

    @GetMapping("/cadastroVeiculo")
    String veiculo(Model model) {
        model.addAttribute("agencias", agenciaService.findAll());
        model.addAttribute("veiculo", new VeiculoDto());
        return "form-veiculo";
    }

    @PostMapping("/cadastrarVeiculo")
    public String createVeiculo(@ModelAttribute VeiculoDto veiculoDto) {
        Boolean veiculoExists = veiculoService.existsByPlaca(veiculoDto.getPlaca());
        if (veiculoExists) {
            return "redirect:/cadastroVeiculo?error=1";
        }

        Boolean veiculoSaved = veiculoService.saveVeiculo(veiculoDto);
        if (!veiculoSaved) {
            return "redirect:/cadastroVeiculo?error=2";
        }

        return "redirect:/cadastroVeiculo?success";
    }

    @GetMapping("/veiculo/alugar/{id}")
    public String alugarVeiculo(@PathVariable("id") Long id, Model model) {
        Veiculo veiculo = veiculoService.findById(id);

        if (veiculo == null) {
            return "redirect:/catalogoVeiculos";
        }

        model.addAttribute("veiculo", veiculo);
        return "reserva";
    }

    @GetMapping("/veiculo/verificar")
    public String verificarVeiculo(@ModelAttribute VeiculoSearchDto searchVeiculoDto, @RequestParam(name = "placa", required = false) String placa, Model model) {
        if (placa != null && !placa.isEmpty()) {
            Veiculo veiculo = veiculoService.findByPlaca(placa);
            if (veiculo == null) {
                return "redirect:/veiculo/verificar?error=1";
            }
            model.addAttribute("veiculo", veiculo);
        } else if (searchVeiculoDto.getPlaca() != null && !searchVeiculoDto.getPlaca().isEmpty()) {
            Veiculo veiculo = veiculoService.findByPlaca(searchVeiculoDto.getPlaca());
            if (veiculo == null) {
                return "redirect:/veiculo/verificar?error=1";
            }
            model.addAttribute("veiculo", veiculo);
        } else if (searchVeiculoDto.getId() != null && searchVeiculoDto.getId() > 0) {
            Veiculo veiculo = veiculoService.findById(searchVeiculoDto.getId());
            if (veiculo == null) {
                return "redirect:/veiculo/verificar?error=1";
            }
            model.addAttribute("veiculo", veiculo);
        } else if (searchVeiculoDto.getStatus() != null && !searchVeiculoDto.getStatus().isEmpty()) {
            model.addAttribute("veiculos", veiculoService.findBySituacao(searchVeiculoDto.getStatus()));
            model.addAttribute("veiculoDto", new VeiculoSearchDto());
            return "verificar-veiculo";
        }

        model.addAttribute("veiculos", veiculoService.findAll());
        model.addAttribute("veiculoDto", new VeiculoSearchDto());
        return "verificar-veiculo";
    }

    @RequestMapping("/veiculo/atualizar/{id}")
    public String atualizarVeiculo(@PathVariable("id") Long id, Model model) {
        Veiculo veiculo = veiculoService.findById(id);
        if (veiculo == null) {
            return "redirect:/catalogoVeiculos";
        }

        model.addAttribute("veiculo", veiculo);
        model.addAttribute("agencias", agenciaService.findAll());
        model.addAttribute("veiculoDto", new VeiculoDto());
        return "alterar-veiculo";
    }

    @PostMapping("/veiculo/atualizar")
    public String updateVeiculo(@ModelAttribute VeiculoDto veiculoDto) {
        Veiculo veiculoUpdated = veiculoService.updateVeiculo(veiculoDto);
        if (veiculoUpdated == null) {
            return "redirect:/catalogoVeiculos";
        }

        return "redirect:/veiculo/atualizar/" + veiculoUpdated.getId() + "?success";
    }

    @GetMapping("/veiculo/devolver/{id}")
    public String devolverVeiculo(@PathVariable("id") Long id, Model model) {
        Veiculo veiculo = veiculoService.findById(id);
        if (veiculo == null) {
            return "redirect:/atualizar-veiculo?error=1";
        }

        model.addAttribute("devolucaoDto", new DevolucaoDto());
        model.addAttribute("veiculo", veiculo);
        return "devolucao";
    }
}

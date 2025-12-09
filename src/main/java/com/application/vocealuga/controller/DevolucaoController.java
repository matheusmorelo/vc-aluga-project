package com.application.vocealuga.controller;

import com.application.vocealuga.dto.DevolucaoDto;
import com.application.vocealuga.entity.Devolucao;
import com.application.vocealuga.entity.Motorista;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.repository.DevolucaoRepository;
import com.application.vocealuga.repository.MotoristaRepository;
import com.application.vocealuga.repository.VeiculoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class DevolucaoController {
    private MotoristaRepository motoristaRepository;
    private DevolucaoRepository devolucaoRepository;
    private VeiculoRepository veiculoRepository;

    public DevolucaoController(MotoristaRepository motoristaRepository, DevolucaoRepository devolucaoRepository, VeiculoRepository veiculoRepository) {
        this.motoristaRepository = motoristaRepository;
        this.devolucaoRepository = devolucaoRepository;
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping(value = "/devolver/veiculo")
    public String devolver(@ModelAttribute DevolucaoDto devolucaoDto, Model model) {
        if (devolucaoDto.getIdVeiculo() == null) {
           return "redirect:/veiculo/verificar?error=1";
        }
        Veiculo veiculo = veiculoRepository.findById(devolucaoDto.getIdVeiculo()).orElseThrow();

        Motorista motorista = motoristaRepository.findByCnh(devolucaoDto.getCnh());
        if (motorista == null) {
            return "redirect:/veiculo/devolver?error=1";
        }

        Devolucao devolucao = new Devolucao();
        devolucao.setCobranca(devolucaoDto.getCobranca());
        devolucao.setDescricao(devolucaoDto.getDescricao());
        devolucao.setMotorista(motorista);

        try {
            veiculo.setStatus("ativo");
            devolucaoRepository.save(devolucao);
            return "redirect:/veiculo/verificar?success=1";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/veiculo/verificar?error=1";
        }
    }
}

package com.application.vocealuga.controller;

import com.application.vocealuga.dto.ManutencaoDto;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.entity.Manutencao;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.repository.FuncionarioRepository;
import com.application.vocealuga.repository.ManutencaoRepository;
import com.application.vocealuga.repository.VeiculoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

@Controller
@EnableWebMvc
public class ManutencaoController {
    private final FuncionarioRepository funcionarioRepository;
    private ManutencaoRepository manutencaoRepository;
    private VeiculoRepository veiculoRepository;


    public ManutencaoController(ManutencaoRepository manutencaoRepository, VeiculoRepository veiculoRepository, FuncionarioRepository funcionarioRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.veiculoRepository = veiculoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }
    
    @GetMapping("/realizarManutencao")
    String manutencao(Model model) {
        List<Veiculo> veiculo = veiculoRepository.findByStatus("ativo");
        model.addAttribute("manutencao", new ManutencaoDto());
        model.addAttribute("veiculos", veiculo);
        return "form-manutencao";
    }
    
    @PostMapping("/realizarManutencao")
    public String realizarManutencao(@ModelAttribute ManutencaoDto manutencaoDto) {
        Veiculo veiculo = veiculoRepository.findById(manutencaoDto.getVeiculoId()).orElseThrow();

        String usernameLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        Funcionario funcionario = funcionarioRepository.findByDocumento(usernameLogged);
        if (funcionario == null) {
            return "redirect:/realizarManutencao?error";
        }

        try {
            Manutencao manutencao = new Manutencao();
            manutencao.setValor(manutencaoDto.getValor());
            manutencao.setDataEntrada(manutencaoDto.getData_entrada());
            manutencao.setDescricao(manutencaoDto.getDescricao());
            manutencao.setDataSaida(manutencaoDto.getData_saida());
            manutencao.setFuncionario(funcionario);
            manutencao.setVeiculo(veiculo);

            veiculo.setStatus("manutencao");
            veiculoRepository.save(veiculo);
            manutencaoRepository.save(manutencao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/realizarManutencao?error";
        }

        return "redirect:/realizarManutencao?success";
    }
}

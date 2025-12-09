package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.VeiculoDto;
import com.application.vocealuga.entity.Agencia;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Veiculo;
import com.application.vocealuga.repository.AgenciaRepository;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.VeiculoRepository;
import com.application.vocealuga.service.VeiculoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServiceImpl implements VeiculoService {
    private VeiculoRepository veiculoRepository;
    private AgenciaRepository agenciaRepository;
    private ClienteRepository clienteRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository, AgenciaRepository agenciaRepository, ClienteRepository clienteRepository) {
        this.veiculoRepository = veiculoRepository;
        this.agenciaRepository = agenciaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> findBySituacao(String situacao) {
        return veiculoRepository.findByStatus(situacao);
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean saveVeiculo(VeiculoDto veiculoDto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(veiculoDto.getPlaca());
        veiculo.setModelo(veiculoDto.getModelo());
        veiculo.setCategoria(veiculoDto.getCategoria());
        veiculo.setKm(veiculoDto.getKm());
        veiculo.setCor(veiculoDto.getCor());
        veiculo.setStatus(veiculoDto.getStatus());
        veiculo.setDescricao(veiculoDto.getDescricao());
        veiculo.setAno(veiculoDto.getAno());
        Agencia agencia = agenciaRepository.findById(veiculoDto.getAgenciaId()).orElse(null);
        veiculo.setAgencia(agencia);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();

            ClienteEntity cliente;
            if (username.length() == 11) {
                cliente = clienteRepository.findByCpf(username);
            } else {
                cliente = clienteRepository.findByCnpj(username);
            }
            veiculo.setCliente(cliente);
        } else {
            String username = principal.toString();

            ClienteEntity cliente;
            if (username.length() == 11) {
                cliente = clienteRepository.findByCpf(username);
            } else {
                cliente = clienteRepository.findByCnpj(username);
            }
            veiculo.setCliente(cliente);
        }

        try {
            veiculoRepository.save(veiculo);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean existsByPlaca(String placa) {
        return veiculoRepository.existsByPlaca(placa);
    }

    public Veiculo findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    public Veiculo updateVeiculo(VeiculoDto veiculoDto) {
        Veiculo veiculo = veiculoRepository.findByPlaca(veiculoDto.getPlaca());
        if (veiculo == null) {
            return null;
        }

        if (veiculo.getStatus() != null && veiculo.getStatus().equals("reservado")) {
            return null;
        }

        if (!veiculoDto.getModelo().isEmpty()) {
            veiculo.setModelo(veiculoDto.getModelo());
        }

        if (veiculoDto.getKm() > 0 && !veiculoDto.getKm().equals(veiculo.getKm())) {
            veiculo.setKm(veiculoDto.getKm());
        }

        if (!veiculoDto.getCategoria().isEmpty()) {
            veiculo.setCategoria(veiculoDto.getCategoria());
        }

        if (!veiculoDto.getCor().isEmpty()) {
            veiculo.setCor(veiculoDto.getCor());
        }

        if (!veiculoDto.getStatus().isEmpty()) {
            veiculo.setStatus(veiculoDto.getStatus());
        }

        if (!veiculoDto.getDescricao().isEmpty()) {
            veiculo.setDescricao(veiculoDto.getDescricao());
        }

        try {
            veiculoRepository.save(veiculo);
            return veiculo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

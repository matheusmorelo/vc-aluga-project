package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.ReservaDto;
import com.application.vocealuga.entity.*;
import com.application.vocealuga.repository.*;
import com.application.vocealuga.service.ReservaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {
    private ClienteRepository clienteRepository;
    private MotoristaRepository motoristaRepository;
    private FuncionarioRepository funcionarioRepository;
    private ReservaRepository reservaRepository;
    private VeiculoRepository  veiculoRepository;
    private AgenciaRepository agenciaRepository;

    public ReservaServiceImpl(ClienteRepository clienteRepository, MotoristaRepository motoristaRepository, FuncionarioRepository funcionarioRepository, ReservaRepository reservaRepository, VeiculoRepository veiculoRepository, AgenciaRepository agenciaRepository) {
        this.clienteRepository = clienteRepository;
        this.motoristaRepository = motoristaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.reservaRepository = reservaRepository;
        this.veiculoRepository = veiculoRepository;
        this.agenciaRepository = agenciaRepository;
    }

    public void saveReserva(ReservaDto reservaDto) {
        String document = reservaDto.getClienteDocument();
        ClienteEntity cliente;
        if (document.length() == 11) {
            cliente = clienteRepository.findByCpf(document);
        } else {
            cliente = clienteRepository.findByCnpj(document);
        }

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        String cnhCondutor = reservaDto.getCnhCondutor();
        Motorista motorista = motoristaRepository.findByCnh(cnhCondutor);
        if (motorista == null) {
            throw new RuntimeException("Motorista não encontrado");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Funcionario funcionario = funcionarioRepository.findByDocumento(username);
        if (funcionario == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Veiculo veiculo = veiculoRepository.findByPlaca(reservaDto.getPlacaVeiculo());
        if (veiculo == null) {
            throw new RuntimeException("Veículo não encontrado");
        }

        Reserva newReserva = new Reserva();
        newReserva.setDataFim(reservaDto.getDataFim());
        newReserva.setDataInicio(reservaDto.getDataInicio());
        newReserva.setCliente(cliente);
        newReserva.setMotorista(motorista);
        newReserva.setFuncionario(funcionario);
        newReserva.setVeiculo(veiculo);
        newReserva.setAgencia(agenciaRepository.findById(reservaDto.getAgenciaId()).orElseThrow());

        try {
            reservaRepository.save(newReserva);
            veiculo.setStatus("reservado");
            veiculoRepository.save(veiculo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Erro ao salvar reserva");
        }
    }

    public void deleteReserva() {
        return;
    }

    public void updateReserva() {
        return;
    }
}

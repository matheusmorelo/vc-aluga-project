package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.FuncionarioDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.FuncionarioRepository;
import com.application.vocealuga.service.FuncionarioService;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    private ClienteRepository clienteRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, ClienteRepository clienteRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Funcionario getFuncionarioById(Long id) {
        return null;
    }

    @Override
    public FuncionarioDto createFuncionario(FuncionarioDto funcionarioDto) throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setDocumento(funcionarioDto.getDocumento());
        funcionario.setCargo(funcionarioDto.getCargo());
        funcionario.setContato(funcionarioDto.getContato());

        String document = funcionarioDto.getDocumento();
        ClienteEntity cliente;
        if (document.length() == 11) {
            cliente = clienteRepository.findByCpf(document);
        } else if (document.length() == 14) {
            cliente = clienteRepository.findByCnpj(document);
        } else {
            throw new Exception("Documento inválido");
        }

        if (cliente == null) {
            String password = getGeneratedPassword(funcionarioDto.getDocumento());
            ClienteEntity newCliente = new ClienteEntity();
            newCliente.setNome(funcionarioDto.getNome());
            newCliente.setCpf(funcionarioDto.getDocumento());
            newCliente.setSenha(password);
            cliente = clienteRepository.save(newCliente);
        }

        try {
            funcionario.setCliente(cliente);
            funcionarioRepository.save(funcionario);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        FuncionarioDto funcionarioResponse = new FuncionarioDto();
        funcionarioResponse.setNome(funcionario.getNome());
        funcionarioResponse.setDocumento(funcionario.getDocumento());
        funcionarioResponse.setCargo(funcionario.getCargo());
        return funcionarioResponse;
    }

    private static String getGeneratedPassword(String documentField) throws Exception {
        String password;
        if (documentField.length() == 11) {
            password = documentField.substring(0, 3);
        } else if (documentField.length() == 14) {
            password = documentField.substring(0, 4);
        } else {
            throw new Exception("Documento inválido");
        }

        return password;
    }
}

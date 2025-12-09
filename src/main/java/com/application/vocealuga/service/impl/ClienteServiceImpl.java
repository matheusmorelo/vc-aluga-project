package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.RegistrationDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Permissao;
import com.application.vocealuga.exceptions.PermissaoNotFoundException;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.PermissaoRepository;
import com.application.vocealuga.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    private PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository, PermissaoRepository permissaoRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.permissaoRepository = permissaoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Iterable<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity getClienteById(Long id) {
        return clienteRepository.getReferenceById(id);
    }

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public boolean saveCliente(RegistrationDto registrationDTO) throws PermissaoNotFoundException {
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome(registrationDTO.getNome());
        clienteEntity.setCpf(registrationDTO.getCpf());
        clienteEntity.setCnpj(registrationDTO.getCnpj());
        clienteEntity.setSenha(passwordEncoder.encode(registrationDTO.getSenha()));
        clienteEntity.setIdade(registrationDTO.getIdade());
        clienteEntity.setContato(registrationDTO.getContato());
        Permissao permissao = permissaoRepository.findById(registrationDTO.getPermissaoId()).orElseThrow(() -> new PermissaoNotFoundException("Permissão não encontrada"));
        clienteEntity.setPermissao(List.of(permissao));

        try {
            clienteRepository.save(clienteEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public void deleteCliente(Long id) {}

    @Override
    public ClienteEntity updateCliente(Long id, ClienteEntity cliente) {
        return null;
    }

    @Override
    public ClienteEntity findByClienteName(String nome) {
        return null;
    }

    public ClienteEntity findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public ClienteEntity findByCnpj(String cnpj) {
        return clienteRepository.findByCnpj(cnpj);
    }

    public Boolean existsByNome(String nome) {
        return clienteRepository.existsByNome(nome);
    }

    public Boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    public Boolean existsByCnpj(String cnpj) {
        return clienteRepository.existsByCnpj(cnpj);
    }
}

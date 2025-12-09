package com.application.vocealuga.service;

import com.application.vocealuga.dto.RegistrationDto;

import com.application.vocealuga.entity.ClienteEntity;

public interface ClienteService {
    Iterable<ClienteEntity> getAllClientes();

    ClienteEntity getClienteById(Long id);

    boolean saveCliente(RegistrationDto registrationDto);

    void deleteCliente(Long id);

    ClienteEntity updateCliente(Long id, ClienteEntity cliente);

    ClienteEntity findByClienteName(String nome);
}

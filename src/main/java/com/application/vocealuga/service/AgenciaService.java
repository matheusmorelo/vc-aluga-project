package com.application.vocealuga.service;

import com.application.vocealuga.dto.AgenciaDto;
import com.application.vocealuga.entity.Agencia;

import java.util.List;

public interface AgenciaService {
    public List<Agencia> findAll();

    public Boolean saveAgencia(AgenciaDto agenciaDto);

    public Boolean existsByContato(String contato);
}

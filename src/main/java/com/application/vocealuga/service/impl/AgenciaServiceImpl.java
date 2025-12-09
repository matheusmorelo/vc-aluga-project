package com.application.vocealuga.service.impl;

import com.application.vocealuga.dto.AgenciaDto;
import com.application.vocealuga.entity.Agencia;
import com.application.vocealuga.repository.AgenciaRepository;
import com.application.vocealuga.service.AgenciaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaServiceImpl implements AgenciaService {
    private AgenciaRepository agenciaRepository;

    public AgenciaServiceImpl(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }

    public List<Agencia> findAll() {
        return agenciaRepository.findAll();
    }

    public Boolean saveAgencia(AgenciaDto agenciaDto) {
        Agencia agencia = new Agencia();
        agencia.setContato(agenciaDto.getContato());
        agencia.setRelatorio(agenciaDto.getRelatorio());

        try {
            agenciaRepository.save(agencia);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean existsByContato(String contato) {
        return agenciaRepository.existsByContato(contato);
    }
}

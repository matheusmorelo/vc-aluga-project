package com.application.vocealuga.service;

import com.application.vocealuga.dto.VeiculoDto;

public interface VeiculoService {
    public Boolean saveVeiculo(VeiculoDto veiculoDto);
    public Boolean existsByPlaca(String placa);
}

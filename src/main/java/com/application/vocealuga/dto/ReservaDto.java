package com.application.vocealuga.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservaDto {
    private String cnhCondutor;
    private Long agenciaId;
    private String placaVeiculo;
    private String clienteDocument;
    private Long idFuncionario;
    private String dataInicio;
    private String dataFim;
}

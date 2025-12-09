package com.application.vocealuga.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDto {
    private String placa;
    private String marca;
    private String modelo;
    private String categoria;
    private String cor;
    private Integer ano;
    private Integer km;
    private String status;
    private String descricao;
    private Long agenciaId;
}

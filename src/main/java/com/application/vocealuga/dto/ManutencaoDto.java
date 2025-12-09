package com.application.vocealuga.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManutencaoDto {
    private String valor;
    private String descricao;
    private String data_entrada;
    private String data_saida;
    private Long veiculoId;
}

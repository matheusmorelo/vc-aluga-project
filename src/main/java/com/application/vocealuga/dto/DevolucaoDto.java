package com.application.vocealuga.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevolucaoDto {
    private String cobranca;
    private String descricao;
    private String dataDevolucao;
    private String cnh;
    private Long idVeiculo;
}

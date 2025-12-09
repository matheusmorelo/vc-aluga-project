package com.application.vocealuga.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDto {
    private String nome;
    private String senha;
    private String cpf;
    private String cnpj;
    private Integer idade;
    private String contato;
    private Long permissaoId;
    private Long clienteType;
}
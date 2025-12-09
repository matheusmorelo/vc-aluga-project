package com.application.vocealuga.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private Integer ano;
    private Integer km;
    @Column(name = "situacao")
    private String status;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "categoria")
    private String categoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agencia")
    private Agencia agencia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_motorista")
    private Motorista motorista;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_manutencao")
    private Funcionario funcionario;
}

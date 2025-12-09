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
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_agencia", referencedColumnName = "id_agencia")
    private Agencia agencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_veiculo", referencedColumnName = "id_veiculo")
    private Veiculo veiculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_motorista", referencedColumnName = "id_motorista")
    private Motorista motorista;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id_funcionario")
    private Funcionario funcionario;

    @Column(name = "data_inicio")
    private String dataInicio;

    @Column(name = "data_fim")
    private String dataFim;
}

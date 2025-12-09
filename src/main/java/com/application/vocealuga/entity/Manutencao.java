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
@Table(name = "manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manutencao")
    private Long id;
    private String valor;
    private String descricao;
    @Column(name = "data_entrada")
    private String dataEntrada;
    @Column(name = "data_saida")
    private String dataSaida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

}

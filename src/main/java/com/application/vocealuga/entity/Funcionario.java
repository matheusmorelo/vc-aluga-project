package com.application.vocealuga.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario {
    private String cargo;
    private String nome;
    private String documento;
    private String contato;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;
}

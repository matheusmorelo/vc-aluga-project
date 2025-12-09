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
@Table(name = "agencia")
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agencia")
    private Long id;

    @Column(name = "contato")
    private String contato;

    @Column(name = "relatorio")
    private String relatorio;


}

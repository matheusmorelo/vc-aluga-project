package com.application.vocealuga.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissao")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Long id;
    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "permissao")
    private List<ClienteEntity> clientes = new ArrayList<>();
}

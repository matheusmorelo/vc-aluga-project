package com.application.vocealuga.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    protected String nome;
    @Column(name = "cpf", nullable = true)
    protected String cpf;
    @Column(name = "cnpj", nullable = true)
    protected String cnpj;
    @Column(name = "senha", nullable = false)
    protected String senha;
    protected Integer idade;
    protected String contato;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "cliente_permissao",
        joinColumns = {@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")},
        inverseJoinColumns = @JoinColumn(name = "id_permissao", referencedColumnName = "id_permissao")
    )
    private Collection<Permissao> permissao;
}

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
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Long id;
    @Column(name = "quantidade_geral")
    private Integer quantidadeGeral;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;
}

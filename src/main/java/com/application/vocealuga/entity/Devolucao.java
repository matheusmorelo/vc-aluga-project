package com.application.vocealuga.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devolucao")
public class Devolucao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucao")
    private Long id;
    private String descricao;
    private String cobranca;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_motorista")
    private Motorista motorista;
}

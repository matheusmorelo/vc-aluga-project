package com.application.vocealuga.repository;

import com.application.vocealuga.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByNome(String nome);
    ClienteEntity findByCpf(String cpf);

    ClienteEntity findByCnpj(String cnpj);

    Boolean existsByNome(String nome);

    Boolean existsByCpf(String cpf);

    Boolean existsByCnpj(String cnpj);
}

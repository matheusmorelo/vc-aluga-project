package com.application.vocealuga.repository;

import com.application.vocealuga.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Permissao findByNome(String nome);
}

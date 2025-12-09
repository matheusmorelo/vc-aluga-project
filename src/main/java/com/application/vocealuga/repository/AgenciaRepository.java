package com.application.vocealuga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.vocealuga.entity.Agencia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
    public Boolean existsByContato(String contato);
}

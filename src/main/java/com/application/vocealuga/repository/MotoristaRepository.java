package com.application.vocealuga.repository;

import com.application.vocealuga.entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    public Motorista findByCnh(String cnh);
}

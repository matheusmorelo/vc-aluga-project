package com.application.vocealuga.repository;

import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByNome(String nome); 
    Funcionario findByCliente(ClienteEntity cliente); 
    Funcionario findByDocumento(String document); 
}
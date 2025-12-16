package com.application.vocealuga.service;

import com.application.vocealuga.dto.FuncionarioDto;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.service.FuncionarioService;
import javax.security.auth.login.LoginException;

public interface FuncionarioService {
    Funcionario getFuncionarioById(Long id);
    FuncionarioDto createFuncionario(FuncionarioDto funcionarioDto) throws Exception;
    Funcionario login(String documento, String senha) throws LoginException;
}

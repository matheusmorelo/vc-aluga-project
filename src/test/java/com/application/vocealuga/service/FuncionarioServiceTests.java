package com.application.vocealuga.service;

import com.application.vocealuga.dto.FuncionarioDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.FuncionarioRepository;
import com.application.vocealuga.service.impl.FuncionarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTests {
    @InjectMocks
    private FuncionarioServiceImpl funcionarioService;
    @Mock
    private FuncionarioRepository funcionarioRepository;
    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void FuncionarioService_ShouldCreateFuncionarioWithSuccess() throws Exception {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setNome("Teste");
        funcionarioDto.setDocumento("12345678910");
        funcionarioDto.setCargo("Teste");

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf("12345678910")
                .senha(funcionarioDto.getDocumento().substring(0, 3))
                .build();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setDocumento(funcionarioDto.getDocumento());
        funcionario.setCargo(funcionarioDto.getCargo());

        when(funcionarioRepository.save(Mockito.any(Funcionario.class))).thenReturn(funcionario);
        when(clienteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(cliente);

        funcionarioService.createFuncionario(funcionarioDto);
        assertThat(funcionario.getNome()).isEqualTo(funcionarioDto.getNome());
        assertThat(funcionario.getDocumento()).isEqualTo(funcionarioDto.getDocumento());
    }

    @Test
    public void FuncionarioService_ShouldThrownInvalidDocumentException() throws Exception {
        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setNome("Teste");
        funcionarioDto.setDocumento("123456789");
        funcionarioDto.setCargo("Teste");

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf(funcionarioDto.getDocumento())
                .senha(funcionarioDto.getDocumento().substring(0, 3))
                .build();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setDocumento(funcionarioDto.getDocumento());
        funcionario.setCargo(funcionarioDto.getCargo());
        assertThatException().isThrownBy(() -> funcionarioService.createFuncionario(funcionarioDto));
        assertThatException().describedAs("Documento inválido");
    }

}

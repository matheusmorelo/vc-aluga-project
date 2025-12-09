package com.application.vocealuga.service;

import com.application.vocealuga.dto.RegistrationDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Permissao;
import com.application.vocealuga.exceptions.PermissaoNotFoundException;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.PermissaoRepository;
import com.application.vocealuga.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

    @InjectMocks
    private ClienteServiceImpl clienteService;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private PermissaoRepository permissaoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private RegistrationDto registrationDto;

    @Test
    public void ClienteService_ShouldCreateClienteWithSuccess_AndNotThrownAnyException() {
        Permissao permissao = Permissao.builder()
                .id(1L)
                .nome("ROLE_CLIENTE")
                .build();

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf("12345678910")
                .permissao(List.of(permissao))
                .senha("123456")
                .contato("12345678910")
                .idade(20)
                .build();

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setNome("Teste");
        registrationDto.setCpf("12345678910");
        registrationDto.setPermissaoId(permissao.getId());
        registrationDto.setSenha("123456");
        registrationDto.setContato("12345678910");
        registrationDto.setIdade(20);

        Mockito.lenient().when(permissaoRepository.findById(registrationDto.getPermissaoId())).thenReturn(Optional.of(permissao));
        when(clienteRepository.save(Mockito.any(ClienteEntity.class))).thenReturn(cliente);

        assertThatCode(() -> clienteService.saveCliente(registrationDto)).doesNotThrowAnyException();
        assertThat(clienteService.saveCliente(registrationDto)).isTrue();
    }

    @Test
    public void ClienteService_ShouldThrownPermissaoNotFound() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setNome("Teste");
        registrationDto.setCpf("12345678910");
        registrationDto.setPermissaoId(1L);
        registrationDto.setSenha("123456");
        registrationDto.setContato("12345678910");
        registrationDto.setIdade(20);

        when(permissaoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> clienteService.saveCliente(registrationDto))
                .isInstanceOf(PermissaoNotFoundException.class)
                .hasMessageContaining("Permissão não encontrada");
    }
}

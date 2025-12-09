package com.application.vocealuga.service;

import com.application.vocealuga.dto.TransactionDto;
import com.application.vocealuga.entity.ClienteEntity;
import com.application.vocealuga.entity.Funcionario;
import com.application.vocealuga.entity.TransactionEntity;
import com.application.vocealuga.repository.ClienteRepository;
import com.application.vocealuga.repository.FuncionarioRepository;
import com.application.vocealuga.repository.TransactionRepository;
import com.application.vocealuga.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Test
    public void TransactionService_ShouldCreateTransactionWithSuccess() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setForma("Dinheiro");
        transactionDto.setDescricao("Teste");
        transactionDto.setIdCliente("12345678910");
        transactionDto.setIdFuncionario("12345678911");

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf("12345678910")
                .build();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Teste");
        funcionario.setDocumento("12345678911");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("12345678911");
        when(clienteRepository.findByCpf(Mockito.any(String.class))).thenReturn(cliente);
        when(funcionarioRepository.findByDocumento(Mockito.any(String.class))).thenReturn(funcionario);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setForma(transactionDto.getForma());
        transactionEntity.setDescricao(transactionDto.getDescricao());
        transactionEntity.setCliente(cliente);
        transactionEntity.setFuncionario(funcionario);
        when(transactionRepository.save(Mockito.any(TransactionEntity.class))).thenReturn(transactionEntity);

        transactionService.saveTransaction(transactionDto);
        assertThat(transactionEntity.getForma()).isEqualTo(transactionDto.getForma());
        assertThat(transactionEntity.getDescricao()).isEqualTo(transactionDto.getDescricao());
    }

    @Test
    public void TransactionService_ShouldNotCreateTransactionWithSuccess() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setForma("Dinheiro");
        transactionDto.setDescricao("Teste");

        transactionDto.setIdCliente("12345678910");
        transactionDto.setIdFuncionario("12345678911");

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf("12345678910")
                .build();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Teste");
        funcionario.setDocumento("12345678911");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("12345678911");
        when(clienteRepository.findByCpf(Mockito.any(String.class))).thenReturn(cliente);
        when(funcionarioRepository.findByDocumento(Mockito.any(String.class))).thenReturn(funcionario);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setForma(transactionDto.getForma());
        transactionEntity.setDescricao(transactionDto.getDescricao());
        transactionEntity.setCliente(cliente);
        transactionEntity.setFuncionario(funcionario);
        when(transactionRepository.save(Mockito.any(TransactionEntity.class))).thenReturn(transactionEntity);


        transactionService.saveTransaction(transactionDto);
        assertThat(transactionEntity.getForma()).isNotEqualTo("Cartão");
        assertThat(transactionEntity.getDescricao()).isNotEqualTo("Teste2");
    }
}

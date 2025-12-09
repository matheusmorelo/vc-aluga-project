package com.application.vocealuga.service;

import com.application.vocealuga.dto.ReservaDto;
import com.application.vocealuga.dto.TransactionDto;
import com.application.vocealuga.entity.*;
import com.application.vocealuga.repository.*;
import com.application.vocealuga.service.ReservaService;
import com.application.vocealuga.service.impl.ReservaServiceImpl;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTests {
    @InjectMocks
    private ReservaServiceImpl reservaService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private ClienteRepository  clienteRepository;
    @Mock
    private FuncionarioRepository funcionarioRepository;
    @Mock
    private VeiculoRepository veiculoRepository;
    @Mock
    private MotoristaRepository motoristaRepository;
    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private AgenciaRepository agenciaRepository;

    @Test
    public void ReservaService_ShouldCreateReservaWithSuccess() {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setAgenciaId(1L);
        reservaDto.setCnhCondutor("123456789");
        reservaDto.setClienteDocument("12345678910");
        reservaDto.setPlacaVeiculo("ABC1234");
        reservaDto.setDataFim("2021-01-01");
        reservaDto.setDataInicio("2021-01-01");

        ClienteEntity cliente = ClienteEntity.builder()
                .nome("Teste")
                .cpf("12345678910")
                .build();

        Motorista motorista = new Motorista();
        motorista.setCnh("123456789");

        Funcionario funcionario = new Funcionario();
        funcionario.setDocumento("12345678911");

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("ABC1234");

        when(clienteRepository.findByCpf(Mockito.any(String.class))).thenReturn(cliente);
        when(motoristaRepository.findByCnh(Mockito.any(String.class))).thenReturn(motorista);
        when(funcionarioRepository.findByDocumento(Mockito.any(String.class))).thenReturn(funcionario);
        Mockito.lenient().when(veiculoRepository.findByPlaca(Mockito.any(String.class))).thenReturn(veiculo);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("12345678911");

        Agencia agencia = new Agencia();
        agencia.setId(1L);
        when(agenciaRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(agencia));

        Reserva reserva = new Reserva();
        reserva.setMotorista(motorista);
        reserva.setCliente(cliente);
        reserva.setFuncionario(funcionario);
        reserva.setVeiculo(veiculo);
        reserva.setAgencia(agencia);

        reservaService.saveReserva(reservaDto);
        assertThat(reserva.getMotorista().getCnh()).isEqualTo(reservaDto.getCnhCondutor());
        assertThat(reserva.getCliente().getCpf()).isEqualTo(reservaDto.getClienteDocument());
        assertThat(reserva.getFuncionario().getDocumento()).isEqualTo("12345678911");
        assertThat(reserva.getVeiculo().getPlaca()).isEqualTo(reservaDto.getPlacaVeiculo());
    }

    @Test
    public void ReservaService_ShouldNotCreateReservaAndThrownExceptionAndMessageIsClienteNaoEncontrado() {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setAgenciaId(1L);
        reservaDto.setCnhCondutor("123456789");
        reservaDto.setClienteDocument("12345678910");
        reservaDto.setPlacaVeiculo("ABC1234");
        reservaDto.setDataFim("2021-01-01");
        reservaDto.setDataInicio("2021-01-01");

        when(clienteRepository.findByCpf(Mockito.any(String.class))).thenReturn(null);

        assertThatRuntimeException().isThrownBy(() -> reservaService.saveReserva(reservaDto));
        assertThatException().describedAs("Cliente não encontrado");
    }
}

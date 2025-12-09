package com.application.vocealuga.repository;

import com.application.vocealuga.entity.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void ClienteRepository_ShouldSaveClienteWithSuccess() {
        //Arrange
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome("Teste");
        clienteEntity.setCpf("12345678910");
        clienteEntity.setContato("214455454");
        clienteEntity.setIdade(20);
        clienteEntity.setSenha("123456");

        //Act
        ClienteEntity savedCliente = clienteRepository.save(clienteEntity);

        //Assert
        assertThat(savedCliente.getId()).isNotNull();
        assertThat(savedCliente.getNome()).isEqualTo(clienteEntity.getNome());
    }

    @Test
    public void ClienteRepository_GetAll_ReturnMoreThanOneCliente() {
        ClienteEntity clienteOne = new ClienteEntity();
        clienteOne.setNome("Teste");
        clienteOne.setCpf("12345678910");
        clienteOne.setContato("214455454");
        clienteOne.setSenha("secret");
        clienteOne.setIdade(20);

        ClienteEntity clienteTwo = new ClienteEntity();
        clienteTwo.setNome("Teste2");
        clienteTwo.setCpf("12345678911");
        clienteTwo.setContato("214455455");
        clienteTwo.setSenha("secret");
        clienteTwo.setIdade(21);

        clienteRepository.save(clienteOne);
        clienteRepository.save(clienteTwo);

        List<ClienteEntity> clientes = clienteRepository.findAll();

        assertThat(clientes.size()).isGreaterThan(1);
        assertThat(clientes.get(0).getNome()).isEqualTo(clienteOne.getNome());
        assertThat(clientes.get(1).getNome()).isEqualTo(clienteTwo.getNome());
    }
}

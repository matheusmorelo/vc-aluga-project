package com.application.vocealuga.controller;

import com.application.vocealuga.controller.AuthController;
import com.application.vocealuga.dto.RegistrationDto;

import com.application.vocealuga.repository.PermissaoRepository;
import com.application.vocealuga.service.ClienteService;
import com.application.vocealuga.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteServiceImpl clienteService;
    @MockBean
    private PermissaoRepository permissaoRepository;

    @Test
    public void shouldReturnLoginPageWithSuccess() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateClienteWithSuccess() throws Exception {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setNome("Teste");
        registrationDto.setCpf("12345678910");
        registrationDto.setClienteType(1L);
        registrationDto.setPermissaoId(1L);
        registrationDto.setIdade(20);
        registrationDto.setContato("12345678911");
        registrationDto.setSenha("123456");

        ResultActions response = this.mockMvc.perform(post("/cadastrar")
                        .contentType("application/x-www-form-urlencoded")
                        .param("nome", registrationDto.getNome())
                        .param("cpf", registrationDto.getCpf())
                        .param("clienteType", registrationDto.getClienteType().toString())
                        .param("permissaoId", registrationDto.getPermissaoId().toString())
                        .param("idade",registrationDto.getIdade().toString())
                        .param("contato", registrationDto.getContato())
                        .param("senha", registrationDto.getSenha())
                        .flashAttr("cliente", registrationDto)
                );

        response.andDo(result -> System.out.println(result.getResponse().getContentAsString()));
        response.andExpect(status().is3xxRedirection());
    }
}

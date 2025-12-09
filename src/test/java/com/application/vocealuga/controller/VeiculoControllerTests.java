package com.application.vocealuga.controller;

import com.application.vocealuga.dto.VeiculoDto;
import com.application.vocealuga.service.impl.AgenciaServiceImpl;
import com.application.vocealuga.service.impl.VeiculoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VeiculoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VeiculoServiceImpl veiculoService;
    @MockBean
    private AgenciaServiceImpl agenciaService;

    @Test
    public void VeiculoController_ShouldCreateVeiculoWithSuccess() throws Exception {
        given(veiculoService.saveVeiculo(ArgumentMatchers.any())).willReturn(true);

        VeiculoDto veiculoDto = new VeiculoDto();
        veiculoDto.setPlaca("ABC1234");
        veiculoDto.setModelo("Teste");
        veiculoDto.setCategoria("Teste");
        veiculoDto.setKm(100);
        veiculoDto.setCor("Teste");
        veiculoDto.setStatus("Teste");
        veiculoDto.setDescricao("Teste");
        veiculoDto.setAno(2021);
        veiculoDto.setAgenciaId(1L);

        ResultActions resultActions = this.mockMvc.perform(post("/cadastrarVeiculo")
                        .contentType("application/x-www-form-urlencoded")
                        .param("placa", veiculoDto.getPlaca())
                        .param("modelo", veiculoDto.getModelo())
                        .param("categoria", veiculoDto.getCategoria())
                        .param("km", veiculoDto.getKm().toString())
                        .param("cor", veiculoDto.getCor())
                        .param("status", veiculoDto.getStatus())
                        .param("descricao", veiculoDto.getDescricao())
                        .param("ano", veiculoDto.getAno().toString())
                        .param("agenciaId", veiculoDto.getAgenciaId().toString())
                        .flashAttr("veiculo", veiculoDto)
        );

        resultActions.andExpect(status().is3xxRedirection());
        resultActions.andExpect(redirectedUrl("/cadastroVeiculo?success"));
    }
}

package com.nicson.financasapi.controller;

import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.enums.TipoTransacao;
import com.nicson.financasapi.exception.GlobalExceptionHandler;
import com.nicson.financasapi.exception.TransacaoNaoEncontradaException;
import com.nicson.financasapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
@Import(GlobalExceptionHandler.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoService service;

    @Test
    void deveListarTransacoes() throws Exception {
        TransacaoResponseDTO response = new TransacaoResponseDTO();
        response.setId(1L);
        response.setDescricao("Salário");
        response.setValor(new BigDecimal("3000.00"));
        response.setData(LocalDate.of(2026, 5, 1));
        response.setTipo(TipoTransacao.RECEITA);
        response.setCategoria("Trabalho");

        when(service.listarTodas()).thenReturn(List.of(response));

        mockMvc.perform(get("/transacoes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Salário"));
    }

    @Test
    void deveCriarTransacao() throws Exception {
        TransacaoResponseDTO response = new TransacaoResponseDTO();
        response.setId(10L);
        response.setDescricao("Freela");
        response.setTipo(TipoTransacao.RECEITA);

        when(service.salvar(org.mockito.ArgumentMatchers.any())).thenReturn(response);

        String payload = """
                {
                  "descricao":"Freela",
                  "valor":900.00,
                  "data":"2026-05-01",
                  "tipo":"RECEITA",
                  "categoria":"Serviços"
                }
                """;

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void deveRetornarErroDeValidacao() throws Exception {
        String payload = """
                {
                  "valor":0,
                  "data":"2026-05-01",
                  "tipo":"RECEITA",
                  "categoria":""
                }
                """;

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Erro de validação"));
    }

    @Test
    void deveRetornarNotFoundQuandoNaoExistir() throws Exception {
        when(service.buscarPorId(99L)).thenThrow(new TransacaoNaoEncontradaException(99L));

        mockMvc.perform(get("/transacoes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Transação com ID 99 não encontrada!"));
    }
}

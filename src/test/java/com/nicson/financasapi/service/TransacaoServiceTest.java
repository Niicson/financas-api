package com.nicson.financasapi.service;

import com.nicson.financasapi.dto.TransacaoDTO;
import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.enums.TipoTransacao;
import com.nicson.financasapi.exception.TransacaoNaoEncontradaException;
import com.nicson.financasapi.mapper.TransacaoMapper;
import com.nicson.financasapi.model.Transacao;
import com.nicson.financasapi.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository repository;
    @Mock
    private TransacaoMapper mapper;
    @InjectMocks
    private TransacaoService service;

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoEncontrada() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(TransacaoNaoEncontradaException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveSalvarTransacaoComSucesso() {
        TransacaoDTO dto = new TransacaoDTO();
        dto.setDescricao("Salário");
        dto.setValor(new BigDecimal("2000.00"));
        dto.setData(LocalDate.now());
        dto.setTipo(TipoTransacao.RECEITA);
        dto.setCategoria("Trabalho");

        Transacao entity = new Transacao();
        Transacao savedEntity = new Transacao();
        savedEntity.setId(1L);
        TransacaoResponseDTO response = new TransacaoResponseDTO();
        response.setId(1L);

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toResponseDTO(savedEntity)).thenReturn(response);

        TransacaoResponseDTO result = service.salvar(dto);

        assertEquals(1L, result.getId());
        verify(repository).save(entity);
    }

    @Test
    void deveListarPorTipoComEntradaMinuscula() {
        Transacao transacao = new Transacao();
        TransacaoResponseDTO response = new TransacaoResponseDTO();
        response.setId(2L);

        when(repository.findByTipo(TipoTransacao.RECEITA)).thenReturn(List.of(transacao));
        when(mapper.toResponseDTOList(List.of(transacao))).thenReturn(List.of(response));

        List<TransacaoResponseDTO> result = service.listarPorTipo("receita");

        assertEquals(1, result.size());
        verify(repository).findByTipo(TipoTransacao.RECEITA);
        verify(mapper).toResponseDTOList(any());
    }
}

package com.nicson.financasapi.mapper;

import com.nicson.financasapi.dto.TransacaoDTO;
import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.enums.TipoTransacao;
import com.nicson.financasapi.model.Transacao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransacaoMapperTest {

    private final TransacaoMapper mapper = new TransacaoMapper();

    @Test
    void deveMapearDtoParaEntidade() {
        TransacaoDTO dto = new TransacaoDTO();
        dto.setDescricao("Salário");
        dto.setValor(new BigDecimal("1500.00"));
        dto.setData(LocalDate.of(2026, 5, 1));
        dto.setTipo(TipoTransacao.RECEITA);
        dto.setCategoria("Trabalho");

        Transacao entity = mapper.toEntity(dto);

        assertEquals(dto.getDescricao(), entity.getDescricao());
        assertEquals(dto.getValor(), entity.getValor());
        assertEquals(dto.getData(), entity.getData());
        assertEquals(dto.getTipo(), entity.getTipo());
        assertEquals(dto.getCategoria(), entity.getCategoria());
    }

    @Test
    void deveMapearEntidadeParaResponseDto() {
        Transacao entity = new Transacao();
        entity.setId(10L);
        entity.setDescricao("Mercado");
        entity.setValor(new BigDecimal("100.00"));
        entity.setData(LocalDate.of(2026, 5, 2));
        entity.setTipo(TipoTransacao.DESPESA);
        entity.setCategoria("Alimentação");

        TransacaoResponseDTO responseDTO = mapper.toResponseDTO(entity);

        assertEquals(entity.getId(), responseDTO.getId());
        assertEquals(entity.getDescricao(), responseDTO.getDescricao());
        assertEquals(entity.getValor(), responseDTO.getValor());
        assertEquals(entity.getData(), responseDTO.getData());
        assertEquals(entity.getTipo(), responseDTO.getTipo());
        assertEquals(entity.getCategoria(), responseDTO.getCategoria());
    }

    @Test
    void deveMapearListaDeEntidades() {
        Transacao entity = new Transacao();
        entity.setId(1L);
        entity.setDescricao("Freela");
        entity.setValor(new BigDecimal("700.00"));
        entity.setData(LocalDate.of(2026, 5, 3));
        entity.setTipo(TipoTransacao.RECEITA);
        entity.setCategoria("Serviços");

        List<TransacaoResponseDTO> result = mapper.toResponseDTOList(List.of(entity));

        assertEquals(1, result.size());
        assertEquals(entity.getId(), result.get(0).getId());
    }
}

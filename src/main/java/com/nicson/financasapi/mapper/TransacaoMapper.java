package com.nicson.financasapi.mapper;

import com.nicson.financasapi.dto.TransacaoDTO;
import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Conversor entre entidade e DTOs de transação.
 */
@Component
public class TransacaoMapper {

    public Transacao toEntity(TransacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        Transacao transacao = new Transacao();
        transacao.setDescricao(dto.getDescricao());
        transacao.setValor(dto.getValor());
        transacao.setData(dto.getData());
        transacao.setTipo(dto.getTipo());
        transacao.setCategoria(dto.getCategoria());
        return transacao;
    }

    public TransacaoResponseDTO toResponseDTO(Transacao transacao) {
        if (transacao == null) {
            return null;
        }
        TransacaoResponseDTO responseDTO = new TransacaoResponseDTO();
        responseDTO.setId(transacao.getId());
        responseDTO.setDescricao(transacao.getDescricao());
        responseDTO.setValor(transacao.getValor());
        responseDTO.setData(transacao.getData());
        responseDTO.setTipo(transacao.getTipo());
        responseDTO.setCategoria(transacao.getCategoria());
        return responseDTO;
    }

    public List<TransacaoResponseDTO> toResponseDTOList(List<Transacao> transacoes) {
        if (transacoes == null || transacoes.isEmpty()) {
            return Collections.emptyList();
        }
        return transacoes.stream().map(this::toResponseDTO).toList();
    }

    public void updateEntity(Transacao entity, TransacaoDTO dto) {
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setData(dto.getData());
        entity.setTipo(dto.getTipo());
        entity.setCategoria(dto.getCategoria());
    }
}

package com.nicson.financasapi.service;

import com.nicson.financasapi.dto.TransacaoDTO;
import com.nicson.financasapi.dto.TransacaoResponseDTO;
import com.nicson.financasapi.enums.TipoTransacao;
import com.nicson.financasapi.exception.BusinessException;
import com.nicson.financasapi.exception.TransacaoNaoEncontradaException;
import com.nicson.financasapi.mapper.TransacaoMapper;
import com.nicson.financasapi.model.Transacao;
import com.nicson.financasapi.repository.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Regras de negócio de transações.
 */
@Service
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

    private final TransacaoRepository repository;
    private final TransacaoMapper mapper;

    public TransacaoService(TransacaoRepository repository, TransacaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TransacaoResponseDTO> listarTodas() {
        List<Transacao> transacoes = repository.findAll();
        log.info("Listando {} transações", transacoes.size());
        return mapper.toResponseDTOList(transacoes);
    }

    public TransacaoResponseDTO buscarPorId(Long id) {
        return mapper.toResponseDTO(obterPorId(id));
    }

    public List<TransacaoResponseDTO> listarPorTipo(String tipo) {
        TipoTransacao tipoTransacao = parseTipo(tipo);
        return mapper.toResponseDTOList(repository.findByTipo(tipoTransacao));
    }

    public TransacaoResponseDTO salvar(TransacaoDTO dto) {
        Transacao transacao = mapper.toEntity(dto);
        Transacao salva = repository.save(transacao);
        log.info("Transação criada com id={}", salva.getId());
        return mapper.toResponseDTO(salva);
    }

    public TransacaoResponseDTO atualizar(Long id, TransacaoDTO dto) {
        Transacao existente = obterPorId(id);
        mapper.updateEntity(existente, dto);
        Transacao atualizada = repository.save(existente);
        log.info("Transação atualizada id={}", id);
        return mapper.toResponseDTO(atualizada);
    }

    public void deletar(Long id) {
        obterPorId(id);
        repository.deleteById(id);
        log.info("Transação removida id={}", id);
    }

    private Transacao obterPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Transação não encontrada id={}", id);
            return new TransacaoNaoEncontradaException(id);
        });
    }

    private TipoTransacao parseTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new BusinessException("Tipo de transação é obrigatório", HttpStatus.BAD_REQUEST);
        }
        try {
            return TipoTransacao.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BusinessException("Tipo de transação inválido: " + tipo, HttpStatus.BAD_REQUEST);
        }
    }
}

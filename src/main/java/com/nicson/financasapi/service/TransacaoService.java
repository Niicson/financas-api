package com.nicson.financasapi.service;

import com.nicson.financasapi.enums.TipoTransacao;
import com.nicson.financasapi.exception.TransacaoNaoEncontradaException;
import com.nicson.financasapi.model.Transacao;
import com.nicson.financasapi.repository.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransacaoService {

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);

    @Autowired
    private TransacaoRepository repository;

    public List<Transacao> listarTodas() {
        log.info("Listando todas as transações");
        return repository.findAll();
    }

    public Transacao buscarPorId(Long id) {
        log.info("Buscando transação com ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new TransacaoNaoEncontradaException(id));
    }

    public List<Transacao> listarPorTipo(TipoTransacao tipo) {
        log.info("Listando transações por tipo: {}", tipo);
        return repository.findByTipo(tipo);
    }

    public List<Transacao> listarPorCategoria(String categoria) {
        log.info("Listando transações por categoria: {}", categoria);
        return repository.findByCategoria(categoria);
    }

    public Transacao salvar(Transacao transacao) {
        log.info("Salvando transação: {}", transacao.getDescricao());
        return repository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoAtualizada) {
        log.info("Atualizando transação com ID: {}", id);
        Transacao transacao = buscarPorId(id);
        transacao.setDescricao(transacaoAtualizada.getDescricao());
        transacao.setValor(transacaoAtualizada.getValor());
        transacao.setData(transacaoAtualizada.getData());
        transacao.setTipo(transacaoAtualizada.getTipo());
        transacao.setCategoria(transacaoAtualizada.getCategoria());
        return repository.save(transacao);
    }

    public void deletar(Long id) {
        log.info("Deletando transação com ID: {}", id);
        buscarPorId(id);
        repository.deleteById(id);
    }
}


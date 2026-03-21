package com.nicson.financasapi.service;

import com.nicson.financasapi.model.Transacao;
import com.nicson.financasapi.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public List<Transacao> listarTodas() {
        return repository.findAll();
    }

    public Transacao buscarPorId(Long id) {
        Optional<Transacao> transacao = repository.findById(id);
        return transacao.orElseThrow(() -> new RuntimeException("Transação não encontrada!"));
    }

    public List<Transacao> listarPorTipo(String tipo) {
        return repository.findByTipo(tipo.toUpperCase());
    }

    public Transacao salvar(Transacao transacao) {
        transacao.setTipo(transacao.getTipo().toUpperCase());
        return repository.save(transacao);
    }

    public Transacao atualizar(Long id, Transacao transacaoAtualizada) {
        Transacao transacao = buscarPorId(id);
        transacao.setDescricao(transacaoAtualizada.getDescricao());
        transacao.setValor(transacaoAtualizada.getValor());
        transacao.setData(transacaoAtualizada.getData());
        transacao.setTipo(transacaoAtualizada.getTipo().toUpperCase());
        transacao.setCategoria(transacaoAtualizada.getCategoria());
        return repository.save(transacao);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

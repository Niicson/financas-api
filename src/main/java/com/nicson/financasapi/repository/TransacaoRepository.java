package com.nicson.financasapi.repository;

import com.nicson.financasapi.model.Transacao;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByTipo(String tipo);

    List<Transacao> findByCategoria(String categoria);

    List<Transacao> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
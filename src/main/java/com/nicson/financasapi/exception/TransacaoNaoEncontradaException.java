package com.nicson.financasapi.exception;

public class TransacaoNaoEncontradaException extends RuntimeException {
    public TransacaoNaoEncontradaException(Long id) {
        super("Transação com ID " + id + " não encontrada!");
    }
}
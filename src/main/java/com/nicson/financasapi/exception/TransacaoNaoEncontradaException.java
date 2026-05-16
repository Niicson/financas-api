package com.nicson.financasapi.exception;

import org.springframework.http.HttpStatus;

public class TransacaoNaoEncontradaException extends BusinessException {
    public TransacaoNaoEncontradaException(Long id) {
        super("Transação com ID " + id + " não encontrada!", HttpStatus.NOT_FOUND);
    }
}

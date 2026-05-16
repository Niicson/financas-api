package com.nicson.financasapi.dto;

import com.nicson.financasapi.enums.TipoTransacao;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransacaoResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private TipoTransacao tipo;
    private String categoria;
}

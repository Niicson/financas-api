package com.nicson.financasapi.dto;

import com.nicson.financasapi.enums.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransacaoDTO {

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Tipo é obrigatório")
    private TipoTransacao tipo;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;
}

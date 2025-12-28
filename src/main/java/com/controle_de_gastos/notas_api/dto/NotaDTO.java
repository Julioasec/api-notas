package com.controle_de_gastos.notas_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record NotaDTO(
        Integer id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        Double total,
        Integer qtdeItens,
        NotasCategoriaDTO categoria,
        EstabelecimentoDTO estabelecimento
) {
}

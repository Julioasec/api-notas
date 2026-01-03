package com.controle_de_gastos.notas_api.dto.resposta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record NotaRespostaDTO(
        Integer id,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        Double total,
        Integer qtdeItens,
        NotasCategoriaRespostaDTO categoria,
        EstabelecimentoRespostaDTO estabelecimento
) {
}

package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record ParcelamentoRespostaDTO(
        Integer id,
        Integer nParcela,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPagamento,
        Double valorParcela,
        Boolean pago,
        NotaSimplesProjecaoDTO nota
) {
}

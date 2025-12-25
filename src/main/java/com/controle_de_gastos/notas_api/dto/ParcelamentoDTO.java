package com.controle_de_gastos.notas_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;


public record ParcelamentoDTO(
        Integer id,
        Integer nParcela,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPagamento,
        Double valorParcela,
        Boolean pago,
        Integer idNota,
        Double total
) {
}

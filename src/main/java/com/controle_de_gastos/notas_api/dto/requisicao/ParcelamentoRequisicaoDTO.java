package com.controle_de_gastos.notas_api.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record ParcelamentoRequisicaoDTO(
        Integer numeroParcela,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataPagamento,
        Boolean pago,
        Double valorParcela
) {
}

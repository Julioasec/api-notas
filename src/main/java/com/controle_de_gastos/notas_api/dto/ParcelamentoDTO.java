package com.controle_de_gastos.notas_api.dto;

import java.util.Date;

public record ParcelamentoDTO(
        Integer idParcelamento,
        Integer nParcela,
        Date dataPagamento,
        Double valorParcela,
        Boolean pago,
        Integer idNota,
        Double total
) {
}

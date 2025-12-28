package com.controle_de_gastos.notas_api.dto;

public record NotaMetodoPagamentoJuncaoDTO(
        Integer id,
        NotaDTO nota,
        MetodoPagamentoDTO metodoPagamento,
        Double valorPago
) {
}

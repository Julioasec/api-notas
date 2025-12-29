package com.controle_de_gastos.notas_api.dto;

public record NotaPagamentoDTO(
        Integer idNotaPagamento,
        String metodoPagamentoNome,
        Double valorPago
) {
}

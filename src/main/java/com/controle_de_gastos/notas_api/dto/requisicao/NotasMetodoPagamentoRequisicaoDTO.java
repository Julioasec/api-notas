package com.controle_de_gastos.notas_api.dto.requisicao;

public record NotasMetodoPagamentoRequisicaoDTO(
        Integer idNota,
        Integer idMetodo,
        Double valorPago
) {
}

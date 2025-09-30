package com.controle_de_gastos.notas_api.dto;

public record NotaMetodoPagamentoJuncaoDTO(
        Integer idNMPagamento,
        NotaDTO nota,
        MetodoPagamentoDTO metodoPagamento
) {
}

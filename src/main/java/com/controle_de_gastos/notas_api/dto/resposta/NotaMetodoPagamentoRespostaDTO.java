package com.controle_de_gastos.notas_api.dto.resposta;

public record NotaMetodoPagamentoRespostaDTO(
        Integer id,
        NotaRespostaDTO nota,
        MetodoPagamentoRespostaDTO metodoPagamento,
        Double valorPago
) {
}

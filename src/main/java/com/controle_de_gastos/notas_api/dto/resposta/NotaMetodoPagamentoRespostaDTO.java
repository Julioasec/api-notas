package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;

public record NotaMetodoPagamentoRespostaDTO(
        Integer id,
        NotaSimplesProjecaoDTO nota,
        MetodoPagamentoRespostaDTO metodoPagamento,
        Double valorPago
) {
}

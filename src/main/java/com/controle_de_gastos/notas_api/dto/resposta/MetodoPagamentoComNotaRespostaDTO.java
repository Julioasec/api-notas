package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;

import java.util.List;

public record MetodoPagamentoComNotaRespostaDTO(
        Integer id,
        String nome,
        List<NotaSimplesProjecaoDTO> notas
) {
}

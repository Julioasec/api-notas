package com.controle_de_gastos.notas_api.dto;

import com.controle_de_gastos.notas_api.dto.projecao.EstabelecimentoEnderecoProjecaoDTO;

public record _refazer_BairroComEstabRespostaDTO(
        Integer id,
        String nome,
        EstabelecimentoEnderecoProjecaoDTO estabelecimento
) {
}

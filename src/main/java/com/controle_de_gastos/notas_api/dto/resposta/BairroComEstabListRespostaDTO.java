package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.EstabelecimentoEnderecoProjecaoDTO;

import java.util.List;

public record BairroComEstabListRespostaDTO(
        Integer id,
        String nome,
        List<EstabelecimentoEnderecoProjecaoDTO> estabelecimentos
) {
}

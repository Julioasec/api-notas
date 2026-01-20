package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.EstabelecimentoComEnderecoProjecaoDTO;

import java.util.List;

public record BairroComEstabRespostaDTO(
        Integer id,
        String nome,
        List<EstabelecimentoComEnderecoProjecaoDTO> estabelecimentos
) {
}

package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.BairroComEnderecoProjecaoDTO;

import java.util.List;

public record EstabelecimentoComBairroRespostaDTO(
        Integer id,
        String nome,
        List<BairroComEnderecoProjecaoDTO> bairros
) {
}

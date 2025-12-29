package com.controle_de_gastos.notas_api.dto;

import java.util.List;

public record BairroEstabListDTO(
        Integer id,
        String nome,
        List<EstabelecimentoSimplesDTO> estabelecimentos
) {
}

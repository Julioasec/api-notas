package com.controle_de_gastos.notas_api.dto;

public record BairroEstabDTO(
        Integer id,
        String nome,
        EstabelecimentoSimplesDTO estabelecimento
) {
}

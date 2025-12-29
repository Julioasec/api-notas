package com.controle_de_gastos.notas_api.dto;

import java.util.List;

public record EstabelecimentoBairroDTO(
        Integer id,
        String nome,
        List<BairroEnderecoDTO> bairros
) {
}

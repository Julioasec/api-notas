package com.controle_de_gastos.notas_api.dto;

public record EstabelecimentoBairroJuncaoDTO(
        Integer idEstabelecimentoBairro,
        BairroDTO bairro,
        EstabelecimentoDTO estabelecimento,
        String endereco
) {
}

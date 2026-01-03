package com.controle_de_gastos.notas_api.dto.resposta;

public record EstabelecimentoBairroRespostaDTO(
        Integer id,
        BairroRespostaDTO bairro,
        EstabelecimentoRespostaDTO estabelecimento,
        String endereco
) {
}

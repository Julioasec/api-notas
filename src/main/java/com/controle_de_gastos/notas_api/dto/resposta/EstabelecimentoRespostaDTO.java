package com.controle_de_gastos.notas_api.dto.resposta;

public record EstabelecimentoRespostaDTO(
        Integer id,
        String nome,
        CategoriaEstabelecimentoRespostaDTO categoria
)
{}

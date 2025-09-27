package com.controle_de_gastos.notas_api.dto;

public record EstabelecimentoDTO(
        Integer idEstabelecimento,
        String nome,
        Integer categoriaId,
        String categoriaNome
)
{}

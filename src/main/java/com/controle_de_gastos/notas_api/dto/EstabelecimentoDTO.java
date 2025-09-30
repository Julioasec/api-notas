package com.controle_de_gastos.notas_api.dto;

import java.util.List;

public record EstabelecimentoDTO(
        Integer id,
        String nome,
        CategoriaEstabelecimentoDTO categoria
)
{}

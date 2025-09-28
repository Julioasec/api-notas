package com.controle_de_gastos.notas_api.dto;

import com.controle_de_gastos.notas_api.model.Nota;

import java.util.List;

public record NotasCategoriaDTO(
        Integer idCategoria,
        String nome
) {
}

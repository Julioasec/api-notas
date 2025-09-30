package com.controle_de_gastos.notas_api.dto;

public record ItemDTO(
    Integer id,
    String nome,
    Double peso,
    String versao,
    ItemTipoDTO tipo,
    MarcaDTO marca
) {}

package com.controle_de_gastos.notas_api.dto;

public record ItemDTO(
    Integer idItem,
    String nome,
    Double peso,
    String versao,
    Integer idTipo,
    String nomeTipo,
    Integer idMarca,
    String nomeMarca
) {}

package com.controle_de_gastos.notas_api.dto;

public record ItemAtributoJuncaoDTO(
        Integer id,
        ItemDTO item,
        AtributoDTO atributo
) {
}

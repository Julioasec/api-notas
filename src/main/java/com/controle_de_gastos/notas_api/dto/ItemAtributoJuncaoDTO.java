package com.controle_de_gastos.notas_api.dto;

public record ItemAtributoJuncaoDTO(
        Integer idItemAtributo,
        ItemDTO item,
        AtributoDTO atributo
) {
}

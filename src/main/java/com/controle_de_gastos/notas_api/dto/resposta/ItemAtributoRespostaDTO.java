package com.controle_de_gastos.notas_api.dto.resposta;

public record ItemAtributoRespostaDTO(
        Integer id,
        ItemRespostaDTO item,
        AtributoRespostaDTO atributo
) {
}

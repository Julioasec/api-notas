package com.controle_de_gastos.notas_api.dto.resposta;

public record NotaItemRespostaDTO(
        Integer id,
        Integer quantidade,
        Double valorUnitario,
        NotaRespostaDTO nota,
        ItemRespostaDTO item
) {
}

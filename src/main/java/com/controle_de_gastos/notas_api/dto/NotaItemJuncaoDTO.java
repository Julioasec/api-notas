package com.controle_de_gastos.notas_api.dto;

import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.Nota;

public record NotaItemJuncaoDTO(
        Integer idNotasItens,
        Integer quantidade,
        Double valorUnitario,
        NotaDTO nota,
        ItemDTO item
) {
}

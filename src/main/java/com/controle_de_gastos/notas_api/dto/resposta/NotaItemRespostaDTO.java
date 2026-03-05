package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.ItemSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;

public record NotaItemRespostaDTO(
        Integer id,
        Integer quantidade,
        Double valorUnitario,
        NotaSimplesProjecaoDTO nota,
        ItemSimplesProjecaoDTO item
) {
}

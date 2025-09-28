package com.controle_de_gastos.notas_api.dto;

import java.util.Date;

public record NotaDTO(
        Integer idNota,
        Date data,
        Double total,
        Integer qtdeItens,
        Integer idCategoria,
        String nomeCategoria/*,
        Integer idEstabelecimento,
        String nomeEstabelecimento
        */
) {
}

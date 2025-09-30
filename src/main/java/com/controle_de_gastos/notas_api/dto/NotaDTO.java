package com.controle_de_gastos.notas_api.dto;

import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.NotasCategoria;

import java.util.Date;

public record NotaDTO(
        Integer id,
        Date data,
        Double total,
        Integer qtdeItens,
        NotasCategoriaDTO categoria,
        EstabelecimentoDTO estabelecimento
) {
}

package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.ItemSimplesProjecaoDTO;

import java.util.List;

public record MarcaComItemRespostaDTO(
        Integer id,
        String nome,
        List<ItemSimplesProjecaoDTO> itens
) {
}

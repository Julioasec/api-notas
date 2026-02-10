package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.AtributoSimplesProjecaoDTO;

import java.util.List;

public record ItemComAtributoListRespostaDTO(
        Integer id,
        List<AtributoSimplesProjecaoDTO> atributo
) {
}

package com.controle_de_gastos.notas_api.dto.resposta;

import com.controle_de_gastos.notas_api.dto.projecao.AtributoSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.projecao.MarcaSimplesDTO;
import com.controle_de_gastos.notas_api.model.Marca;

import java.util.List;

public record ItemComAtributoListRespostaDTO(
        Integer id,
        String nome,
        Double peso,
        String versao,
        List<AtributoSimplesProjecaoDTO> atributos
) {
}

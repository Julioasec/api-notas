package com.controle_de_gastos.notas_api.dto.resposta;

import java.util.List;

public record ItemRespostaDTO(
        Integer id,
        String nome,
        Double peso,
        String versao,
        ItemTipoRespostaDTO tipo,
        MarcaRespostaDTO marca,
        List<AtributoRespostaDTO> atributos
) {}

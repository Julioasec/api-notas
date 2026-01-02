package com.controle_de_gastos.notas_api.dto.resposta;

public record ItemRespostaDTO(
    Integer id,
    String nome,
    Double peso,
    String versao,
    ItemTipoRespostaDTO tipo,
    MarcaRespostaDTO marca
) {}

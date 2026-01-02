package com.controle_de_gastos.notas_api.dto.requisicao;

public record ItemRequisicaoDTO(
        Integer idTipo,
        Integer idMarca,
        String nome,
        Double peso,
        String versao
) {

}

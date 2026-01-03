package com.controle_de_gastos.notas_api.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record NotaRequisicaoDTO(
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        Double total,
        Integer qtdeItens,
        Integer idEstabelecimento,
        Integer idCategoria
) {

}

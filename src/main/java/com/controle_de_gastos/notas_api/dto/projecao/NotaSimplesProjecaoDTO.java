package com.controle_de_gastos.notas_api.dto.projecao;

import java.time.LocalDate;

public record NotaSimplesProjecaoDTO(
        Integer id,
        LocalDate data,
        Double valorTotal
) {
}

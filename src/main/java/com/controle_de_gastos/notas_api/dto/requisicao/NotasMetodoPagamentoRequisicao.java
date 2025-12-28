package com.controle_de_gastos.notas_api.dto.requisicao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NotasMetodoPagamentoRequisicao {
    private Integer idNota;
    private Integer idMetodo;
    private Double valorPago;
}

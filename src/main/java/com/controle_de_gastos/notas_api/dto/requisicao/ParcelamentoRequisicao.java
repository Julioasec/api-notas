package com.controle_de_gastos.notas_api.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
    @AllArgsConstructor
    @NoArgsConstructor
public class ParcelamentoRequisicao {
    private Integer numeroParcela;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;
    private Boolean pago;
    private double valorParcela;
}

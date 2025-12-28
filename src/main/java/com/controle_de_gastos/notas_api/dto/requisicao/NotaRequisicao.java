package com.controle_de_gastos.notas_api.dto.requisicao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotaRequisicao {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private Double total;
    private Integer qtdeItens;
    private Integer idEstabelecimento;
    private Integer idCategoria;

}

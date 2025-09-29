package com.controle_de_gastos.notas_api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoBairroRequisicao {
    private Integer estabelecimentoId;
    private Integer bairroId;
    private String endereco;
}

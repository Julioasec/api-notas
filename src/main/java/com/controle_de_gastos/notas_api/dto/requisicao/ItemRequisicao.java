package com.controle_de_gastos.notas_api.dto.requisicao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequisicao {
    private Integer idTipo;
    private Integer idMarca;
    private String nome;
    private Double peso;
    private String versao;
}

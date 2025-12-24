package com.controle_de_gastos.notas_api.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoRequisicao {
    private int estabelecimentoId;
    private int categoriaId;
    private String nome;

}

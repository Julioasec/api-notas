package com.controle_de_gastos.notas_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Categoria {
    private long idCategoria;
    private String nome;
}

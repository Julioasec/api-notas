package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
    @Table(name = "Item_Tipo")
public class ItemTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;
    private String nome;
}

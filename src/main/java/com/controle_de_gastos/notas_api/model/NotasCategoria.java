package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notas_Categoria")
public class NotasCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategoria;
    private String nome;
}

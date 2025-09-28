package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private ItemTipo tipo;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    String nome;
    Double peso;
    String versao;
}

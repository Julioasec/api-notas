package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    @Table(name = "Item_Tipo")
public class ItemTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private Set<Item> itens;

}

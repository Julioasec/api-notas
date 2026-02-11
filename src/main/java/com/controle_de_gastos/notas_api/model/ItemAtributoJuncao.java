package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Item_Atributo")
public class ItemAtributoJuncao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_atributo")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_atributo")
    private Atributo atributo;
    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;
}

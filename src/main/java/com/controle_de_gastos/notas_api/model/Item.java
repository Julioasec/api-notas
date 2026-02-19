package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private ItemTipo tipo;
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemAtributoJuncao> itemAtributoJuncaos = new HashSet<>();
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NotaItemJuncao>  notaItemJuncaos = new HashSet<>();
    String nome;
    Double peso;
    String versao;


}

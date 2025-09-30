package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemAtributoJuncao> itemAtributoJuncaos = new HashSet<>();
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NotaItemJuncao>  notaItemJuncaos = new HashSet<>();
    String nome;
    Double peso;
    String versao;


}

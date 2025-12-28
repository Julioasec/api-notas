package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstabelecimento;
    private String nome;
    @ManyToOne // indica que vários estabelecimentos podem ter 1 categoria
    @JoinColumn(name = "id_categoria_estabelecimento") // indica a chave estrangeira no banco, que referencia CategoriaEstabelecimento
    private CategoriaEstabelecimento categoria;

    @OneToMany(mappedBy = "estabelecimento",  cascade = CascadeType.ALL)
    private List<Nota> notas;

   @OneToMany(mappedBy = "estabelecimento",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EstabelecimentoBairroJuncao> estabelecimentoBairroJuncaos = new HashSet<>();
}


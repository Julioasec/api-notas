package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estabelecimento")
    private Integer id;
    private String nome;
    @ManyToOne // indica que vários estabelecimentos podem ter 1 categoria
    @JoinColumn(name = "id_categoria_estabelecimento") // indica a chave estrangeira no banco, que referencia CategoriaEstabelecimento
    private CategoriaEstabelecimento categoria;

    @Builder.Default
    @OneToMany(mappedBy = "estabelecimento",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "estabelecimento",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EstabelecimentoBairroJuncao> estabelecimentoBairroJuncaos = new HashSet<>();
}


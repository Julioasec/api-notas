package com.controle_de_gastos.notas_api.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Categoria_Estabelecimento")
public class CategoriaEstabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_estabelecimento")
    private Integer id;
    private String nome;
    @Builder.Default
    @OneToMany(mappedBy ="categoria", cascade = CascadeType.ALL) // uma Categoria para muitos estabelecimentos
    private Set<Estabelecimento> estabelecimentos = new HashSet<>(); // representa os estabelecimentos associados a categoria
}

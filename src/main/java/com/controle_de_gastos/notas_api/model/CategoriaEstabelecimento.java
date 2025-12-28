package com.controle_de_gastos.notas_api.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Categoria_Estabelecimento")
public class CategoriaEstabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaEstabelecimento;
    private String nome;
    @OneToMany(mappedBy ="categoria", cascade = CascadeType.ALL) // uma Categoria para muitos estabelecimentos
    private Set<Estabelecimento> estabelecimentos; // representa os estabelecimentos associados a categoria
}

package com.controle_de_gastos.notas_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstabelecimento;
    private String nome;
    @ManyToOne // indica que vários estabelecimentos podem ter 1 categoria
    @JoinColumn(name = "id_categoria_estabelecimento") // indica a chave estrangeira no banco, que referencia CategoriaEstabelecimento
    @JsonBackReference // Impede que o JSON serialize cada Set de categoria e crie um loop infinito
    private CategoriaEstabelecimento categoria;
}

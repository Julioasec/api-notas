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
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer id;
    private String nome;
    @Builder.Default
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private Set<Item> itens = new HashSet<>();
}

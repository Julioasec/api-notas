package com.controle_de_gastos.notas_api.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bairro")
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "bairro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EstabelecimentoBairroJuncao> estabelecimentoBairroJuncaos =  new HashSet<>();
}

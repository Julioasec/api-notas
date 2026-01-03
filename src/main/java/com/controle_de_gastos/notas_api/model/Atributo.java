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
    public class Atributo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_atributo")
        private Integer id;
        private String nome;

        @OneToMany(mappedBy = "atributo", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<ItemAtributoJuncao> itemAtributoJuncaos = new HashSet<>();
    }

package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Atributo {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idAtributo;
        private String nome;
    }

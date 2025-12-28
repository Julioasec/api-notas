package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "notas_metodo_pagamento")
public class NotaMetodoPagamentoJuncao {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_notas_metodo")
        private Integer idNMPagamento;

        @ManyToOne
        @JoinColumn(name = "id_nota")
        private Nota nota;

        @ManyToOne
        @JoinColumn(name = "id_metodo")
        private MetodoPagamento metodoPagamento;
        Double valorPago;

}

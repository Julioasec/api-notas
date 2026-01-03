
package com.controle_de_gastos.notas_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcelamento")
    private Integer id;
    @Column(name = "n_parcela")
    private Integer parcela;
    @Column(name = "data_pagamento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;
    private Boolean pago;
    private Double valorParcela;
    @ManyToOne // significa que muitos parcelamentos podem estar associados a 1 nota
    @JoinColumn(name = "id_nota")
    private Nota nota;
}


package com.controle_de_gastos.notas_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParcelamento;
    @Column(name = "n_parcela")
    private Integer parcela;
    @Column(name = "data_pagamento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataPagamento;
    private Boolean pago = false;
    private Double valorParcela;
    @ManyToOne // significa que muitos parcelamentos podem estar associados a 1 nota
    @JoinColumn(name = "id_nota")
    private Nota nota;
}

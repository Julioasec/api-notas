/*
package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParcelamento;

    @ManyToOne // significa que muitos parcelamentos podem estar associados a 1 nota
    @JoinColumn(name = 'id_nota')
    private  idNota;
    private Integer nParcela;
    private Date dataPagamento;
    private boolean pago = false;


}
*/
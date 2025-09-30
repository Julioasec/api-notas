package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notas_itens")
public class NotaItemJuncao {
    @Id
    @Column(name = "id_notas_itens")
    private Integer idNotasItens;
    private Integer quantidade;
    private Double valorUnitario;

    @ManyToOne
    @JoinColumn(name = "id_nota")
    private Nota nota;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;
}

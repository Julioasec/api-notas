package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNota;
    private Date data;
    private Double total;
    private int qtdeItens;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private NotasCategoria categoria;
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL)
    private Set<Parcelamento> parcelamentos;

    /*@ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;
     */
}

package com.controle_de_gastos.notas_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private Double total;
    private int qtdeItens;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private NotasCategoria categoria;
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL)
    private Set<Parcelamento> parcelamentos;

    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NotaMetodoPagamentoJuncao> notaMetodoPagamentoJuncaos = new HashSet<>();

    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NotaItemJuncao> notaItemJuncaos = new HashSet<>();
}

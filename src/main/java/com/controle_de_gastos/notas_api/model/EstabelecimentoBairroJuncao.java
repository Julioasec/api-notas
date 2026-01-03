package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "estabelecimento_bairro")
public class EstabelecimentoBairroJuncao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estabelecimento_bairro")
    private Integer id;
    private String endereco;
    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;


}

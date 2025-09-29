package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "estabelecimento_bairro")
public class EstabelecimentoBairroJuncao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estabelecimento_bairro")
    private Integer idEstabelecimentoBairro;
    private String endereco;
    @ManyToOne
    @JoinColumn(name = "id_estabelecimento")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;


}

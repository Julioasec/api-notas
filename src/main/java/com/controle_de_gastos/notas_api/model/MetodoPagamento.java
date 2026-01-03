package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Metodo_Pagamento")
public class MetodoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo")
    private Integer id;
    private String nome;

    @OneToMany(mappedBy = "metodoPagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<NotaMetodoPagamentoJuncao>  notaMetodoPagamentoJuncaos = new HashSet<>();
}

package com.controle_de_gastos.notas_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Metodo_Pagamento")
public class MetodoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo")
    private Integer id;
    private String nome;
    @Builder.Default
    @OneToMany(mappedBy = "metodoPagamento", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<NotaMetodoPagamentoJuncao>  notaMetodoPagamentoJuncaos = new HashSet<>();
}

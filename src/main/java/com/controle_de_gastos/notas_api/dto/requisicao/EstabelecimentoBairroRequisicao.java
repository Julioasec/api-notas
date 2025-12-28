package com.controle_de_gastos.notas_api.dto.requisicao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoBairroRequisicao {
    private Integer estabelecimentoId;
    private Integer bairroId;
    private String endereco;
}

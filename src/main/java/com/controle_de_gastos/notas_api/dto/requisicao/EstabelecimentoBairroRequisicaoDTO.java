package com.controle_de_gastos.notas_api.dto.requisicao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


public record EstabelecimentoBairroRequisicaoDTO(
         Integer idEstabelecimento,
         Integer idBairro,
         String endereco
) {

}

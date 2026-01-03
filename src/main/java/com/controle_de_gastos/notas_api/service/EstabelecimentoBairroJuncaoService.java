package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoBairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.BairroRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoBairroRespostaDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstabelecimentoBairroJuncaoService {

    private final BairroRepository bairroRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoBairroJuncaoRepository estabelecimentoBairroJuncaoRepository;
    private final BairroService bairroService;
    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoBairroRespostaDTO toRespostaDTO(EstabelecimentoBairroJuncao juncao){
        return new EstabelecimentoBairroRespostaDTO(
                juncao.getId(),
                bairroService.toRespostaDTO(juncao.getBairro()),
                estabelecimentoService.toRespostaDTO(juncao.getEstabelecimento()),
               juncao.getEndereco()
       );
    }
    
    public List<EstabelecimentoBairroRespostaDTO> listarTodos(){
        return estabelecimentoBairroJuncaoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<EstabelecimentoBairroRespostaDTO> buscarPorid(Integer id){
        return estabelecimentoBairroJuncaoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public EstabelecimentoBairroRespostaDTO associar(EstabelecimentoBairroRequisicaoDTO juncao){

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(juncao.idEstabelecimento())
                .orElseThrow(()->new RuntimeException("Estabelecimento não encontrado"));

        Bairro bairro = bairroRepository.findById(juncao.idBairro())
                .orElseThrow(()->new RuntimeException("Bairro não encontrado"));

        EstabelecimentoBairroJuncao ebJuncao = new EstabelecimentoBairroJuncao();

        estabelecimento.getEstabelecimentoBairroJuncaos().add(ebJuncao);
        bairro.getEstabelecimentoBairroJuncaos().add(ebJuncao);

        ebJuncao.setEndereco(juncao.endereco());
        ebJuncao.setEstabelecimento(estabelecimento);
        ebJuncao.setBairro(bairro);
        return  toRespostaDTO(estabelecimentoBairroJuncaoRepository.save(ebJuncao));
    }

    public void deletarPorId(Integer id){
        estabelecimentoBairroJuncaoRepository.deleteById(id);
    }
}

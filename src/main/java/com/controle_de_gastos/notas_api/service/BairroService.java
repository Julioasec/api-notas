package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.BairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.BairroRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.BairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto._refazer_BairroComEstabRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroComEstabListRespostaDTO;
import com.controle_de_gastos.notas_api.dto.projecao.EstabelecimentoEnderecoProjecaoDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;
    private final EstabelecimentoBairroJuncaoRepository estabelecimentoBairroJuncaoRepository;
    private final EstabelecimentoService estabelecimentoService;

    public BairroRespostaDTO toRespostaDTO(Bairro bairro){
            return new BairroRespostaDTO(
                    bairro.getId(),
                    bairro.getNome()
            );
    }

    public _refazer_BairroComEstabRespostaDTO toBairroComEstabRespostaDTO(EstabelecimentoBairroJuncao estabBairro){
        return new _refazer_BairroComEstabRespostaDTO(
                estabBairro.getBairro().getId(),
                estabBairro.getBairro().getNome(),
                estabelecimentoService.toEstabSimplesDTO(estabBairro.getEstabelecimento(), estabBairro.getEndereco())
        );
    }

    public List<BairroRespostaDTO> listarTodos() {
        return bairroRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<BairroRespostaDTO> buscarPorId(Integer id){
        return bairroRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    // Corrigir
    public List<_refazer_BairroComEstabRespostaDTO> listarEstabPorBairroId(Integer id){
        return estabelecimentoBairroJuncaoRepository.findByBairroId(id)
                .stream()
                .map(this::toBairroComEstabRespostaDTO)
                .toList();
    }

    public List<BairroComEstabListRespostaDTO> listarTodosEstabPorBairro(){
        return bairroRepository.findAll()
                .stream()
                .map(bairro -> new BairroComEstabListRespostaDTO(
                        bairro.getId(),
                        bairro.getNome(),
                        bairro.getEstabelecimentoBairroJuncaos()
                                .stream()
                                .map(juncao -> new EstabelecimentoEnderecoProjecaoDTO(
                                        juncao.getEstabelecimento().getId(),
                                        juncao.getEstabelecimento().getNome(),
                                        juncao.getEndereco()
                                ))
                                .toList()

                ))
                .toList();

    }
    public BairroRespostaDTO criar(BairroRequisicaoDTO bairroRequisicaoDTO){
        Bairro  bairro = new Bairro();
        bairro.setNome(bairroRequisicaoDTO.nome());
         return toRespostaDTO(bairroRepository.save(bairro));
    }

    public void  deletarPorId(Integer id){
        bairroRepository.deleteById(id);
    }

}

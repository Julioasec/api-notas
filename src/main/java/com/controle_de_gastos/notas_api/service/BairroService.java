package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.BairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.BairroRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.BairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroComEstabRespostaDTO;
import com.controle_de_gastos.notas_api.dto.projecao.EstabelecimentoComEnderecoProjecaoDTO;
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

    public BairroRespostaDTO toRespostaDTO(Bairro bairro){
            return new BairroRespostaDTO(
                    bairro.getId(),
                    bairro.getNome()
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

    public Optional<BairroComEstabRespostaDTO> listarEstabPorBairroId(Integer id){
        Bairro bairro = bairroRepository.findById(id).orElse(null);

        if(bairro == null){
            return Optional.empty();
        }

        List<EstabelecimentoComEnderecoProjecaoDTO> estabelecimentos = estabelecimentoBairroJuncaoRepository.findByBairroId(id)
                .stream()
                .map(data -> new EstabelecimentoComEnderecoProjecaoDTO(
                                data.getEstabelecimento().getId(),
                                data.getEstabelecimento().getNome(),
                                data.getEndereco()
                        )
                        )
                .toList();
        return Optional.of( new BairroComEstabRespostaDTO(
                bairro.getId(),
                bairro.getNome(),
                estabelecimentos
        ));

    }

    public List<BairroComEstabRespostaDTO> listarTodosEstabPorBairro(){
        return bairroRepository.findAll()
                .stream()
                .map(bairro -> new BairroComEstabRespostaDTO(
                        bairro.getId(),
                        bairro.getNome(),
                        bairro.getEstabelecimentoBairroJuncaos()
                                .stream()
                                .map(juncao -> new EstabelecimentoComEnderecoProjecaoDTO(
                                        juncao.getEstabelecimento().getId(),
                                        juncao.getEstabelecimento().getNome(),
                                        juncao.getEndereco()
                                ))
                                .toList()

                ))
                .toList();

    }
    public BairroRespostaDTO criar(BairroRequisicaoDTO bairroDTO){
        Bairro  bairro = Bairro.builder()
                .nome(bairroDTO.nome())
                .build();
         return toRespostaDTO(bairroRepository.save(bairro));
    }

    public Optional<BairroRespostaDTO> atualizarTudo(Integer id, BairroRequisicaoDTO bairroDTO){
        Bairro bairro = bairroRepository.findById(id).orElse(null);
        if(bairro == null) return Optional.empty();

        bairro.setNome(bairroDTO.nome());
        return Optional.of(toRespostaDTO(bairroRepository.save(bairro)));
    }

    public boolean deletarPorId(Integer id){
        Optional<Bairro> bairro = bairroRepository.findById(id);

        if(bairro.isEmpty()) return false;

        if(!bairro.get().getEstabelecimentoBairroJuncaos().isEmpty()){
               throw new IllegalStateException("Não é possivel deletar, existem dependências");
        }

        bairroRepository.deleteById(id);
        return true;
    }

}

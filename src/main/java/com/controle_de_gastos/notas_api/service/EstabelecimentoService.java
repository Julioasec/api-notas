package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.projecao.BairroComEnderecoProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoComBairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;
    private final CategoriaEstabelecimentoService categoriaEstabelecimentoService;
    private final EstabelecimentoBairroJuncaoRepository estabelecimentoBairroJuncaoRepository;

    public EstabelecimentoRespostaDTO toRespostaDTO(Estabelecimento estabelecimento) {
        return new EstabelecimentoRespostaDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                categoriaEstabelecimentoService.toRespostaDTO(estabelecimento.getCategoria())
        );
    }

    public List<EstabelecimentoRespostaDTO> listarTodos(){
        return estabelecimentoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<EstabelecimentoComBairroRespostaDTO> listarBairroPorEstabelecimentoId(Integer id) {
               List <EstabelecimentoBairroJuncao> juncoes = estabelecimentoBairroJuncaoRepository.findByEstabelecimentoId(id);

               if(juncoes.isEmpty()){
                   return null;
               }

               Estabelecimento estabelecimento = juncoes.get(0).getEstabelecimento();
               List<BairroComEnderecoProjecaoDTO> bairros = juncoes.stream()
                       .map(data -> new BairroComEnderecoProjecaoDTO(
                               data.getBairro().getId(),
                               data.getBairro().getNome(),
                               data.getEndereco()
                       ))
                       .toList();

                return Optional.of(new EstabelecimentoComBairroRespostaDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome(),
                        bairros
                ));
    }

    public List<EstabelecimentoComBairroRespostaDTO> listarTodosBairroPorEstabelecimento(){
            List<EstabelecimentoComBairroRespostaDTO> juncaos = estabelecimentoBairroJuncaoRepository.findAll()
                    .stream()
                    .collect(Collectors.groupingBy(
                            data -> data.getEstabelecimento().getId()
                    ))
                    .values()
                    .stream()
                    .map(data ->{
                        Estabelecimento estabelecimento = data.get(0).getEstabelecimento();

                                List<BairroComEnderecoProjecaoDTO> bairros = data
                                        .stream()
                                        .map( juncao -> new BairroComEnderecoProjecaoDTO(
                                                    juncao.getBairro().getId(),
                                                    juncao.getBairro().getNome(),
                                                    juncao.getEndereco()
                                            ))
                                        .toList();

                                        return new EstabelecimentoComBairroRespostaDTO(
                                                estabelecimento.getId(),
                                                estabelecimento.getNome(),
                                                bairros
                                        );
                    })
                    .toList();

            return juncaos;
    }

    public EstabelecimentoRespostaDTO criar(EstabelecimentoRequisicaoDTO estabelecimentoDto) {
        CategoriaEstabelecimento categoria = categoriaEstabelecimentoRepository.findById(estabelecimentoDto.idCategoria())
                .orElseThrow(()-> new RuntimeException("Categoria Inválida"));

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setCategoria(categoria);
        estabelecimento.setNome(estabelecimentoDto.nome());
        return toRespostaDTO(estabelecimentoRepository.save(estabelecimento));
    }

    public Optional<EstabelecimentoRespostaDTO> buscarPorId(Integer id){
        return estabelecimentoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public void deletarPorId(Integer id){
        estabelecimentoRepository.deleteById(id);
    }



}

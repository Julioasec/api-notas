package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoBairroJuncaoDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoDTO;
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
    private final CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    public EstabelecimentoBairroJuncaoDTO toDTO(EstabelecimentoBairroJuncao eB){
        BairroDTO bairroDTO = new BairroDTO(
                eB.getBairro().getIdBairro(),
                eB.getBairro().getNome());

        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO(
                            eB.getEstabelecimento().getIdEstabelecimento(),
                            eB.getEstabelecimento().getNome(),
                            categoriaEstabelecimentoService.toDTO(eB.getEstabelecimento().getCategoria()));



        return new EstabelecimentoBairroJuncaoDTO(
               eB.getIdEstabelecimentoBairro(),
               bairroDTO,
               estabelecimentoDTO,
               eB.getEndereco()
       );
    }
    
    public List<EstabelecimentoBairroJuncaoDTO> listarTodos(){
        return estabelecimentoBairroJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<EstabelecimentoBairroJuncaoDTO> buscarPorid(Integer idEstabelecimentoBairro){
        return estabelecimentoBairroJuncaoRepository.findById(idEstabelecimentoBairro)
                .map(this::toDTO);
    }

    public EstabelecimentoBairroJuncaoDTO associar(
                                                  Integer idEstabelecimento,
                                                  Integer idBairro,
                                                  String endereco){

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento)
                .orElseThrow(()->new RuntimeException("Estabelecimento não encontrado"));

        Bairro bairro = bairroRepository.findById(idBairro)
                .orElseThrow(()->new RuntimeException("Bairro não encontrado"));

        EstabelecimentoBairroJuncao estabelecimentoBairroJuncao = new EstabelecimentoBairroJuncao();

        estabelecimento.getEstabelecimentoBairroJuncaos().add(estabelecimentoBairroJuncao);
        bairro.getEstabelecimentoBairroJuncaos().add(estabelecimentoBairroJuncao);
        estabelecimentoBairroJuncao.setEndereco(endereco);

        estabelecimentoBairroJuncao.setEstabelecimento(estabelecimento);
        estabelecimentoBairroJuncao.setBairro(bairro);
        return  toDTO(estabelecimentoBairroJuncaoRepository.save(estabelecimentoBairroJuncao));

    }

    public void deletarPorId(Integer idEstabelecimentoBairro){
        estabelecimentoBairroJuncaoRepository.deleteById(idEstabelecimentoBairro);
    }
}

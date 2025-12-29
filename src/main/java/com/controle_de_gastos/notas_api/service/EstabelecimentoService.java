package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.BairroEnderecoDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoBairroDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoSimplesDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoRequisicao;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;
    @Autowired
    private CategoriaEstabelecimentoService categoriaEstabelecimentoService;
    @Autowired
    private EstabelecimentoBairroJuncaoRepository estabelecimentoBairroJuncaoRepository;

    public EstabelecimentoDTO toDTO(Estabelecimento estabelecimento) {
        return new EstabelecimentoDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                categoriaEstabelecimentoService.toDTO(estabelecimento.getCategoria())
        );
    }

    public EstabelecimentoSimplesDTO toEstabSimplesDTO(Estabelecimento estabelecimento, String endereco) {
        return new EstabelecimentoSimplesDTO(
                estabelecimento.getId(),
                estabelecimento.getNome(),
                endereco
        );
    }


    public List<EstabelecimentoDTO> listarTodos(){
        return estabelecimentoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public EstabelecimentoBairroDTO listarBairroPorEstabelecimentoId(Integer id) {
               List <EstabelecimentoBairroJuncao> juncoes = estabelecimentoBairroJuncaoRepository.findByEstabelecimentoId(id);

               if(juncoes.isEmpty()){
                   return null;
               }

               Estabelecimento estabelecimento = juncoes.get(0).getEstabelecimento();
               List<BairroEnderecoDTO> bairros = juncoes.stream()
                       .map(data -> new BairroEnderecoDTO(
                               data.getBairro().getId(),
                               data.getBairro().getNome(),
                               data.getEndereco()
                       ))
                       .toList();

                return new EstabelecimentoBairroDTO(
                        estabelecimento.getId(),
                        estabelecimento.getNome(),
                        bairros
                );
    }



    public EstabelecimentoDTO salvarEstabelecimento(EstabelecimentoRequisicao estabelecimentoReq) {
        CategoriaEstabelecimento categoria = categoriaEstabelecimentoRepository.findById(estabelecimentoReq.getCategoriaId())
                .orElseThrow(()-> new RuntimeException("Categoria Inválida"));

        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setCategoria(categoria);
        estabelecimento.setNome(estabelecimentoReq.getNome());
        return toDTO(estabelecimentoRepository.save(estabelecimento));
    }

    public Optional<Estabelecimento> buscarPorId(Integer id){
        return estabelecimentoRepository.findById(id);
    }

    public void deletarPorId(Integer id){
        estabelecimentoRepository.deleteById(id);
    }



}

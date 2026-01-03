package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.repository.NotaMetodoPagametoJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.NotaRepository;
import com.controle_de_gastos.notas_api.repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.NotaRespostaDTO;
import com.controle_de_gastos.notas_api.dto._refazer_NotaPagamentoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotaRequisicaoDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.model.NotaMetodoPagamentoJuncao;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final NotasCategoriaRepository notasCategoriaRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoService estabelecimentoService;
    private final NotasCategoriaService notasCategoriaService;
    private final NotaMetodoPagametoJuncaoRepository notaMetodoPagametoJuncaoRepository;

    public NotaRespostaDTO toRespostaDTO(Nota nota){
        return new NotaRespostaDTO(
                nota.getId(),
                nota.getData(),
                nota.getTotal(),
                nota.getQtdeItens(),
                notasCategoriaService.toRespostaDTO(nota.getCategoria()),
                estabelecimentoService.toRespostaDTO(nota.getEstabelecimento())
        );
    }

    public _refazer_NotaPagamentoDTO toNotaPagamentoDTO(NotaMetodoPagamentoJuncao notaMPJuncao){
        return new _refazer_NotaPagamentoDTO(
                notaMPJuncao.getId(),
                notaMPJuncao.getMetodoPagamento().getNome(),
                notaMPJuncao.getValorPago()
        );
    }

    public List<NotaRespostaDTO> listarTodos(){
        return notaRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<NotaRespostaDTO> buscarPorid(Integer id){
        return notaRepository.findById(id)
                .map(this::toRespostaDTO);
    }


    //refazer
    public List<_refazer_NotaPagamentoDTO> listarPagamentosPorNota(Integer id){
        return notaMetodoPagametoJuncaoRepository.findByNotaId(id)
                .stream()
                .map(this::toNotaPagamentoDTO)
                .toList();
    }

    public NotaRespostaDTO criar(NotaRequisicaoDTO notaRequisicaoDTO){
        NotasCategoria categoria = notasCategoriaRepository.findById(notaRequisicaoDTO.idCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria Não Encontrada"));

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(notaRequisicaoDTO.idEstabelecimento())
                        .orElseThrow(()->new RuntimeException("Estabelecimento não encontrado"));

        Nota nota = new Nota();
        nota.setCategoria(categoria);
        nota.setEstabelecimento(estabelecimento);
        nota.setData(notaRequisicaoDTO.data());
        nota.setTotal(notaRequisicaoDTO.total());
        nota.setQtdeItens(notaRequisicaoDTO.qtdeItens());

        return toRespostaDTO(notaRepository.save(nota));
    }

    public void deletarPorId(Integer id){
        notaRepository.deleteById(id);
    }



}

package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.Nota;
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

    public NotaDTO toDTO(Nota nota){
        return new NotaDTO(
                nota.getIdNota(),
                nota.getData(),
                nota.getTotal(),
                nota.getQtdeItens(),
                notasCategoriaService.toDTO(nota.getCategoria()),
                estabelecimentoService.toDTO(nota.getEstabelecimento())
        );
    }

    public List<NotaDTO> listarTodos(){
        return notaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<NotaDTO> buscarPorid(Integer id){
        return notaRepository.findById(id)
                .map(this::toDTO);
    }

    public Nota salvarNota(Nota nota, Integer idCategoria, Integer idEstabelecimento){
        NotasCategoria categoria = notasCategoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria Não Encontrada"));

        Estabelecimento estabelecimento = estabelecimentoRepository.findById(idEstabelecimento)
                        .orElseThrow(()->new RuntimeException("Estabelecimento não encontrado"));

        nota.setCategoria(categoria);
        nota.setEstabelecimento(estabelecimento);
        return notaRepository.save(nota);
    }

    public void deletarPorId(Integer id){
        notaRepository.deleteById(id);
    }



}

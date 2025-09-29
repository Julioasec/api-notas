package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.Repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EstabelecimentoService {
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;
    private final BairroRepository bairroRepository;

    private EstabelecimentoDTO toDTO(Estabelecimento estabelecimento) {
        return new EstabelecimentoDTO(
                estabelecimento.getIdEstabelecimento(),
                estabelecimento.getNome(),
                estabelecimento.getCategoria().getIdCategoriaEstabelecimento(),
                estabelecimento.getCategoria().getNome()
        );
    }


    public List<EstabelecimentoDTO> listarTodos(){
        return estabelecimentoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Estabelecimento salvarEstabelecimento(Estabelecimento estabelecimento, Integer categoriaId) {
        CategoriaEstabelecimento categoria = categoriaEstabelecimentoRepository.findById(categoriaId)
                .orElseThrow(()-> new RuntimeException("Categoria Inválida"));

        estabelecimento.setCategoria(categoria);
        return estabelecimentoRepository.save(estabelecimento);
    }

    public Optional<Estabelecimento> buscarPorId(Integer id){
        return estabelecimentoRepository.findById(id);
    }

    public void deletarPorId(Integer id){
        estabelecimentoRepository.deleteById(id);
    }



}

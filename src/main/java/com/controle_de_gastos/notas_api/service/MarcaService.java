package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.MarcaRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.MarcaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.MarcaRespostaDTO;
import com.controle_de_gastos.notas_api.model.Marca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaRespostaDTO toRespostaDTO(Marca marca){
        return new MarcaRespostaDTO(
                marca.getId(),
                marca.getNome()
        );
    }

    public List<MarcaRespostaDTO> listarTodos(){
        return marcaRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<MarcaRespostaDTO> buscarPorId(Integer id){
        return marcaRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public MarcaRespostaDTO criar(MarcaRequisicaoDTO marcaDTO){
        Marca marca = Marca.builder()
                .nome(marcaDTO.nome())
                .build();
        return toRespostaDTO(marcaRepository.save(marca));
    }

    public Optional<MarcaRespostaDTO> atualizarTudo(Integer id, MarcaRequisicaoDTO marcaDTO){
            return marcaRepository.findById(id)
                    .map(marca -> {
                        marca.setNome(marcaDTO.nome());
                        return toRespostaDTO(marcaRepository.save(marca));
                    });
    }

    public boolean deletarPorId(Integer id){
        Marca marca = marcaRepository.findById(id).orElse(null);
        if(marca == null) return false;

        if(!marca.getItens().isEmpty()) throw new IllegalStateException("Não é possivel deletar, existem dependências");

        marcaRepository.deleteById(id);
        return true;
    }
}

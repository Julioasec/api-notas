package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.NotasCategoriaRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.NotasCategoriaRespostaDTO;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotasCategoriaService {

    private final NotasCategoriaRepository notasCategoriaRepository;

    public NotasCategoriaRespostaDTO toRespostaDTO(NotasCategoria notasCategoria){
                return new NotasCategoriaRespostaDTO(
                        notasCategoria.getId(),
                        notasCategoria.getNome());
    }

    public List<NotasCategoriaRespostaDTO> listarTodos(){
        return notasCategoriaRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<NotasCategoriaRespostaDTO> buscarPorId(Integer id){
        return notasCategoriaRepository.findById(id)
                .map(this::toRespostaDTO);
    }


    public NotasCategoriaRespostaDTO criar(NotasCategoriaRequisicaoDTO notasCategoriaDTO){
              NotasCategoria notasCategoria = NotasCategoria.builder()
                      .nome(notasCategoriaDTO.nome())
                      .build();
              return toRespostaDTO(notasCategoriaRepository.save(notasCategoria));

    }

    public Optional<NotasCategoriaRespostaDTO> atualizarTudo(Integer id, NotasCategoriaRequisicaoDTO categoriaDTO){
            Optional<NotasCategoria> notasCategoria = notasCategoriaRepository.findById(id);
            if(notasCategoria.isEmpty()) return Optional.empty();

            notasCategoria.get().setNome(categoriaDTO.nome());
            return Optional.of(toRespostaDTO(notasCategoriaRepository.save(notasCategoria.get())));
    }
    public boolean deletarPorId(Integer id){
        Optional<NotasCategoria> notasCategoria = notasCategoriaRepository.findById(id);
        if(notasCategoria.isEmpty()) return false;
        if(!notasCategoria.get().getNotas().isEmpty()) throw new IllegalStateException("Nao é possível deletar, existem dependências");

        notasCategoriaRepository.deleteById(id);
        return true;
    }
}

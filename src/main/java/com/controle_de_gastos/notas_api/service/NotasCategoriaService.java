package com.controle_de_gastos.notas_api.service;

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


    public NotasCategoriaRespostaDTO criar(NotasCategoriaRespostaDTO notasCategoriaRespostaDTO){
              NotasCategoria notasCategoria = new NotasCategoria();
              notasCategoria.setNome(notasCategoriaRespostaDTO.nome());
              return toRespostaDTO(notasCategoriaRepository.save(notasCategoria));

    }

    public void deletarPorId(Integer id){
        notasCategoriaRepository.deleteById(id);
    }
}

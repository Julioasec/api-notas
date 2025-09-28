package com.controle_de_gastos.notas_api.service;


import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.dto.NotasCategoriaDTO;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotasCategoriaService {
    private final NotasCategoriaRepository notasCategoriaRepository;
    @Autowired
    private final NotaService notaService;

    public NotasCategoriaDTO toDTO(NotasCategoria notasCategoria){
                return new NotasCategoriaDTO(
                        notasCategoria.getIdCategoria(),
                        notasCategoria.getNome());
    }

    public List<NotasCategoriaDTO> listarTodos(){
        return notasCategoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<NotasCategoriaDTO> buscarPorId(Integer id){
        return notasCategoriaRepository.findById(id)
                .map(this::toDTO);
    }


    public NotasCategoriaDTO salvarCategoria(NotasCategoria notasCategoria){
              NotasCategoria categoriaSalva =  notasCategoriaRepository.save(notasCategoria);
              return toDTO(categoriaSalva);

    }

    public void deletarPorId(Integer id){
        notasCategoriaRepository.deleteById(id);
    }
}

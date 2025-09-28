package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private NotaRepository notaRepository;
    private NotasCategoriaRepository notasCategoriaRepository;

    public NotaDTO toDTO(Nota nota){
        return new NotaDTO(
                nota.getIdNota(),
                nota.getData(),
                nota.getTotal(),
                nota.getQtdeItens(),
                nota.getCategoria().getIdCategoria(),
                nota.getCategoria().getNome()
        );
    }

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
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

    public Nota salvarNota(Nota nota, Integer idCategoria){
        NotasCategoria categoria = notasCategoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria Não Encontrada"));

        nota.setCategoria(categoria);
        return notaRepository.save(nota);
    }

    public void deletarPorId(Integer id){
        notaRepository.deleteById(id);
    }



}

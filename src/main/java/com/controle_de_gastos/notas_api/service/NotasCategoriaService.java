package com.controle_de_gastos.notas_api.service;


import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotasCategoriaService {
    private final NotasCategoriaRepository notasCategoriaRepository;

    public NotasCategoriaService(NotasCategoriaRepository notasCategoriaRepository){
        this.notasCategoriaRepository = notasCategoriaRepository;
    }

    public List<NotasCategoria> listarTodos(){
        return notasCategoriaRepository.findAll();
    }

    public Optional<NotasCategoria> buscarPorId(Integer id){
        return notasCategoriaRepository.findById(id);
    }


    public NotasCategoria salvar(NotasCategoria notasCategoria){
        return notasCategoriaRepository.save(notasCategoria);
    }

    public void deletarPorId(Integer id){
        notasCategoriaRepository.deleteById(id);
    }
}

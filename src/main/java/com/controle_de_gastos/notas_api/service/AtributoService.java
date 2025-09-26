package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.AtributoRepository;
import com.controle_de_gastos.notas_api.model.Atributo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtributoService {
    private final AtributoRepository atributoRepository;

    public AtributoService(AtributoRepository atributoRepository) {
        this.atributoRepository = atributoRepository;
    }

    public List<Atributo> listarTodos() {
        return atributoRepository.findAll();
    }

    public Optional<Atributo> buscarPorId(Long id) {
        return atributoRepository.findById(id);
    }

    public Atributo salvar(Atributo atributo){
        return atributoRepository.save(atributo);
    }

    public void deletarPorId(Long id){
        atributoRepository.deleteById(id);
    }
}

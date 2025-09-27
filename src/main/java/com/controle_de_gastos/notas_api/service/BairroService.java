package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.model.Bairro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BairroService {
    private final BairroRepository bairroRepository;

    public BairroService(BairroRepository bairroRepository){
        this.bairroRepository = bairroRepository;
    };

    public List<Bairro> listarTodos() {
        return bairroRepository.findAll();
    }

    public Optional<Bairro> buscarPorId(Integer id){
        return bairroRepository.findById(id);
    }

    public Bairro salvar(Bairro bairro){
         return bairroRepository.save(bairro);
    }

    public void  deletarPorId(Integer id){
        bairroRepository.deleteById(id);
    }

}

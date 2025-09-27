package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MarcaRepository;
import com.controle_de_gastos.notas_api.model.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    private MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> listarTodos(){
        return marcaRepository.findAll();
    }

    public Optional<Marca> buscarPorId(Integer idMarca){
        return marcaRepository.findById(idMarca);
    }

    public Marca salvarMarca(Marca marca){
        return marcaRepository.save(marca);
    }

    public void deletarPorId(Integer idMarca){
        marcaRepository.deleteById(idMarca);
    }
}

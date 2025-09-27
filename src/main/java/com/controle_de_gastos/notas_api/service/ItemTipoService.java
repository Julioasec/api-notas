package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemTipoService {
    private ItemTipoRepository itemTipoRepository;

    public ItemTipoService(ItemTipoRepository itemTipoRepository){
        this.itemTipoRepository = itemTipoRepository;
    }

    public List<ItemTipo> listarTodos(){
        return itemTipoRepository.findAll();
    }

    public Optional<ItemTipo> buscarPorId(Integer id){
        return itemTipoRepository.findById(id);
    }

    public ItemTipo salvarTipo(ItemTipo tipo){
       return itemTipoRepository.save(tipo);
    }

    public void deletarPorId(Integer id){
        itemTipoRepository.deleteById(id);
    }

}

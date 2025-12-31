package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.dto.ItemTipoDTO;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemTipoService {

    private final ItemTipoRepository itemTipoRepository;

    public ItemTipoDTO toDTO(ItemTipo itemTipo){
        return new ItemTipoDTO(
                itemTipo.getId(),
                itemTipo.getNome()
        );
    }

    public List<ItemTipoDTO> listarTodos(){
        return itemTipoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ItemTipoDTO> buscarPorId(Integer id){
        return itemTipoRepository.findById(id)
                .map(this::toDTO);
    }

    public ItemTipoDTO salvarTipo(ItemTipoDTO tipoDTO){
        ItemTipo tipo = new ItemTipo();
        tipo.setNome(tipoDTO.nome());
       return toDTO(itemTipoRepository.save(tipo));
    }

    public void deletarPorId(Integer id){
        itemTipoRepository.deleteById(id);
    }

}

package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.AtributoRepository;
import com.controle_de_gastos.notas_api.Repository.ItemAtributoJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.ItemRepository;
import com.controle_de_gastos.notas_api.dto.ItemAtributoJuncaoDTO;
import com.controle_de_gastos.notas_api.model.Atributo;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.ItemAtributoJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemAtributoJuncaoService {
    @Autowired
    private ItemAtributoJuncaoRepository itemAtributoJuncaoRepository;
    @Autowired
    private AtributoRepository atributoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private AtributoService atributoService;

    public ItemAtributoJuncaoDTO toDTO(ItemAtributoJuncao iAJuncao){
       return new ItemAtributoJuncaoDTO(
               iAJuncao.getIdItemAtributo(),
                itemService.toDTO(iAJuncao.getItem()),
                atributoService.toDTO(iAJuncao.getAtributo())
        );
    }

    public List<ItemAtributoJuncaoDTO> listarTodos(){
        return itemAtributoJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ItemAtributoJuncaoDTO salvarAtribuicao(ItemAtributoJuncao itemAtributoJuncao){
        Item item = itemRepository.findById(itemAtributoJuncao.getItem().getIdItem())
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        Atributo atributo = atributoRepository.findById(itemAtributoJuncao.getAtributo().getIdAtributo())
                .orElseThrow(()-> new RuntimeException("Atributo não encontrado"));

        ItemAtributoJuncao iAJuncao = new ItemAtributoJuncao();

        iAJuncao.setItem(item);
        iAJuncao.setAtributo(atributo);
        iAJuncao.getItem().getItemAtributoJuncaos().add(iAJuncao);
        iAJuncao.getAtributo().getItemAtributoJuncaos().add(iAJuncao);

        return toDTO(itemAtributoJuncaoRepository.save(iAJuncao));
    }


    public Optional<ItemAtributoJuncaoDTO> buscarPorid(Integer idIAJuncao){
        return itemAtributoJuncaoRepository.findById(idIAJuncao)
                .map(this::toDTO);
    }

    public void deletePorId(Integer idIAJuncao){
        itemAtributoJuncaoRepository.deleteById(idIAJuncao);
    }

}

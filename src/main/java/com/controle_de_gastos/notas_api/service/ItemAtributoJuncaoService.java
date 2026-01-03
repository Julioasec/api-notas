package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemAtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.AtributoRepository;
import com.controle_de_gastos.notas_api.repository.ItemAtributoJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.ItemRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributoRespostaDTO;
import com.controle_de_gastos.notas_api.model.Atributo;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.ItemAtributoJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemAtributoJuncaoService {

    private final ItemAtributoJuncaoRepository itemAtributoJuncaoRepository;
    private final AtributoRepository atributoRepository;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final AtributoService atributoService;

    public ItemAtributoRespostaDTO toRespostaDTO(ItemAtributoJuncao juncao){
       return new ItemAtributoRespostaDTO(
               juncao.getId(),
                itemService.toRespostaDTO(juncao.getItem()),
                atributoService.toRespostaDTO(juncao.getAtributo())
        );
    }

    public List<ItemAtributoRespostaDTO> listarTodos(){
        return itemAtributoJuncaoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public ItemAtributoRespostaDTO associar(ItemAtributoRequisicaoDTO juncao){
        Item item = itemRepository.findById(juncao.idItem())
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        Atributo atributo = atributoRepository.findById(juncao.idAtributo())
                .orElseThrow(()-> new RuntimeException("Atributo não encontrado"));

        ItemAtributoJuncao iAJuncao = new ItemAtributoJuncao();

        atributo.getItemAtributoJuncaos().add(iAJuncao);
        item.getItemAtributoJuncaos().add(iAJuncao);

        iAJuncao.setItem(item);
        iAJuncao.setAtributo(atributo);
        iAJuncao.getItem().getItemAtributoJuncaos().add(iAJuncao);
        iAJuncao.getAtributo().getItemAtributoJuncaos().add(iAJuncao);

        return toRespostaDTO(itemAtributoJuncaoRepository.save(iAJuncao));
    }


    public Optional<ItemAtributoRespostaDTO> buscarPorid(Integer id){
        return itemAtributoJuncaoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public void deletePorId(Integer idIAJuncao){
        itemAtributoJuncaoRepository.deleteById(idIAJuncao);
    }

}

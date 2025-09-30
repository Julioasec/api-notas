package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.AtributoRepository;
import com.controle_de_gastos.notas_api.Repository.ItemAtributoJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.ItemRepository;
import com.controle_de_gastos.notas_api.dto.AtributoDTO;
import com.controle_de_gastos.notas_api.dto.ItemAtributoJuncaoDTO;
import com.controle_de_gastos.notas_api.dto.ItemDTO;
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
    private final ItemTipoService itemTipoService;
    private final MarcaService marcaService;


    public ItemAtributoJuncaoDTO toDTO(ItemAtributoJuncao iAJuncao){
        AtributoDTO atributoDTO = new AtributoDTO(
                iAJuncao.getAtributo().getIdAtributo(),
                iAJuncao.getAtributo().getNome()
        );

        ItemDTO itemDTO = new ItemDTO(
                iAJuncao.getItem().getIdItem(),
                iAJuncao.getItem().getNome(),
                iAJuncao.getItem().getPeso(),
                iAJuncao.getItem().getVersao(),
                itemTipoService.toDTO(iAJuncao.getItem().getTipo()),
                marcaService.toDTO(iAJuncao.getItem().getMarca())
        );

        return new ItemAtributoJuncaoDTO(
                iAJuncao.getIdItemAtributo(),
                itemDTO,
                atributoDTO
        );
    }

    public List<ItemAtributoJuncaoDTO> listarTodos(){
        return itemAtributoJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ItemAtributoJuncaoDTO salvarAtribuicao(ItemAtributoJuncao iAJuncao, Integer idItem, Integer idAtributo){
        Item item = itemRepository.findById(idItem)
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        Atributo atributo = atributoRepository.findById(idAtributo)
                .orElseThrow(()-> new RuntimeException("Atributo não encontrado"));

        iAJuncao.setItem(item);
        iAJuncao.setAtributo(atributo);

        iAJuncao.getItem().getItemAtributoJuncaos().add(iAJuncao);
        iAJuncao.getAtributo().getItemAtributoJuncaos().add(iAJuncao);


        return toDTO(itemAtributoJuncaoRepository.save(iAJuncao))

                ;
    }


    public Optional<ItemAtributoJuncaoDTO> buscarPorid(Integer idIAJuncao){
        return itemAtributoJuncaoRepository.findById(idIAJuncao)
                .map(this::toDTO);
    }

    public void deletePorId(Integer idIAJuncao){
        itemAtributoJuncaoRepository.deleteById(idIAJuncao);
    }

}

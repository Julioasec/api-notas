package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.repository.ItemAtributoJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.ItemRepository;
import com.controle_de_gastos.notas_api.repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.repository.MarcaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributosRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.ItemAtributoJuncao;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.model.Marca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;
    private final ItemTipoRepository itemTipoRepository;
    private final MarcaRepository marcaRepository;
    private final ItemTipoService itemTipoService;
    private final MarcaService marcaService;
    private final ItemAtributoJuncaoRepository itemAtributoJuncaoRepository;

    public ItemRespostaDTO toRespostaDTO(Item item){
        return new ItemRespostaDTO(
                item.getId(),
                item.getNome(),
                item.getPeso(),
                item.getVersao(),
                itemTipoService.toRespostaDTO(item.getTipo()),
                marcaService.toRespostaDTO(item.getMarca())
        );
    }

    public ItemAtributosRespostaDTO toItemComAtributoDTO(ItemAtributoJuncao itemAtributoJuncao){
        return new ItemAtributosRespostaDTO(
                itemAtributoJuncao.getItem().getId(),
                itemAtributoJuncao.getAtributo().getId(),
                itemAtributoJuncao.getAtributo().getNome()
        );
    }

    public List<ItemRespostaDTO> listartodos(){
        return itemRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public List<ItemAtributosRespostaDTO> listarAtributosPorItem(Integer id){
        return itemAtributoJuncaoRepository.findByItemId(id)
                .stream()
                .map(this::toItemComAtributoDTO)
                .toList();
    }

    public Optional<ItemRespostaDTO> buscarPorId(Integer id){
        return itemRepository.findById(id)
                .map(this::toRespostaDTO);

    }


    public ItemRespostaDTO salvarItem(ItemRequisicaoDTO itemDTO){
        ItemTipo tipo = itemTipoRepository.findById(itemDTO.idTipo())
                .orElseThrow(()-> new RuntimeException("Tipo não encontrado"));
        Marca marca = marcaRepository.findById(itemDTO.idMarca())
                .orElseThrow(()-> new RuntimeException("Marca não encontrada"));

        Item item = new Item();

        item.setTipo(tipo);
        item.setMarca(marca);
        item.setNome(itemDTO.nome());
        item.setPeso(itemDTO.peso());
        item.setVersao(itemDTO.versao());

        return toRespostaDTO(itemRepository.save(item));
    }

    public void deletarPorID(Integer id){
        itemRepository.deleteById(id);
    }
}

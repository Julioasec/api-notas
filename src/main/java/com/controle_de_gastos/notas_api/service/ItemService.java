package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.Repository.ItemRepository;
import com.controle_de_gastos.notas_api.Repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.Repository.MarcaRepository;
import com.controle_de_gastos.notas_api.dto.ItemDTO;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.model.Marca;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private ItemTipoRepository itemTipoRepository;
    private MarcaRepository marcaRepository;

    private ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public ItemDTO toDTO(Item item){
        return new ItemDTO(
                item.getIdItem(),
                item.getNome(),
                item.getPeso(),
                item.getVersao(),
                item.getTipo().getIdTipo(),
                item.getTipo().getNome(),
                item.getMarca().getIdMarca(),
                item.getMarca().getNome()
        );
    }

    public List<ItemDTO> listartodos(){
        return itemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ItemDTO> buscarPorId(Integer id){
        return itemRepository.findById(id)
                .map(this::toDTO);

    }

    public Item salvarItem(Item item,Integer idTipo, Integer idMarca ){
        ItemTipo tipo = itemTipoRepository.findById(idTipo)
                .orElseThrow(()-> new RuntimeException("Tipo não encontrado"));
        Marca marca = marcaRepository.findById(idMarca)
                .orElseThrow(()-> new RuntimeException("Marca não encontrada"));
        item.setTipo(tipo);
        item.setMarca(marca);
        return itemRepository.save(item);
    }

    public void deletarPorID(Integer id){
        itemRepository.deleteById(id);
    }
}

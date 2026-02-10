package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.dto.projecao.AtributoSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemComAtributoListRespostaDTO;
import com.controle_de_gastos.notas_api.repository.ItemAtributoJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.ItemRepository;
import com.controle_de_gastos.notas_api.repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.repository.MarcaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.model.Item;
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


    public List<ItemRespostaDTO> listartodos(){
        return itemRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<ItemComAtributoListRespostaDTO> listarAtributosPorItemId(Integer id){
        Optional<Item> item =  itemRepository.findById(id);
        if(item.isEmpty()) return Optional.empty();

        List<AtributoSimplesProjecaoDTO> atributos = itemAtributoJuncaoRepository.findByItemId(id)
                .stream()
                .map(juncao -> new AtributoSimplesProjecaoDTO(
                        juncao.getAtributo().getId(),
                        juncao.getAtributo().getNome()
                ))
                .toList();
        return Optional.of(new ItemComAtributoListRespostaDTO(
                item.get().getId(),
                atributos
        ));
    }

    public Optional<ItemRespostaDTO> buscarPorId(Integer id){
        return itemRepository.findById(id)
                .map(this::toRespostaDTO);

    }


    public ItemRespostaDTO criarItem(ItemRequisicaoDTO itemDTO){
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

    public Optional<ItemRespostaDTO> atualizarTudo(Integer id, ItemRequisicaoDTO itemDTO){
        Item item = itemRepository.findById(id).orElse(null);

        if(item == null) return Optional.empty();

        ItemTipo novoTipo = itemTipoRepository.findById(itemDTO.idTipo())
                .orElseThrow(()->new IllegalStateException("Item não encontrado"));

        Marca novaMarca = marcaRepository.findById(itemDTO.idMarca())
                .orElseThrow(()-> new IllegalStateException("Marca não encontrada"));

        if(!item.getTipo().getId().equals(novoTipo.getId())){
            item.setTipo(novoTipo);
        }

        if (!item.getMarca().getId().equals(novaMarca.getId())){
            item.setMarca(novaMarca);
        }

        item.setNome(itemDTO.nome());
        item.setPeso(itemDTO.peso());
        item.setVersao(itemDTO.versao());

        return Optional.of(toRespostaDTO(itemRepository.save(item)));

    }

    public boolean deletarPorID(Integer id){
        Optional<Item> item = itemRepository.findById(id);

        if(item.isEmpty())  return false;

        if(!item.get().getItemAtributoJuncaos().isEmpty() || !item.get().getNotaItemJuncaos().isEmpty()){
            throw new RuntimeException("Não é possível deletar, existem dependências");
        }
                       itemRepository.deleteById(id);
               return true;
    }
}
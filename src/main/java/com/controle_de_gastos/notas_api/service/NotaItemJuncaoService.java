package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.ItemRepository;
import com.controle_de_gastos.notas_api.Repository.NotaItemJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.ItemDTO;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.dto.NotaItemJuncaoDTO;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.model.NotaItemJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaItemJuncaoService {
    private final NotaService notaService;
    private final ItemService itemService;
    private final NotaItemJuncaoRepository notaItemJuncaoRepository;
    private final NotaRepository notaRepository;
    private final ItemRepository itemRepository;

    public NotaItemJuncaoDTO toDTO(NotaItemJuncao notaItemJuncao) {
        NotaDTO notaDTO = notaService.toDTO(notaItemJuncao.getNota());
        ItemDTO itemDTO = itemService.toDTO(notaItemJuncao.getItem());


        return new NotaItemJuncaoDTO(
                    notaItemJuncao.getId(),
                    notaItemJuncao.getQuantidade(),
                    notaItemJuncao.getValorUnitario(),
                    notaDTO,
                    itemDTO
            );
    }

    public List<NotaItemJuncaoDTO> listarTodos(){
        return notaItemJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<NotaItemJuncaoDTO> buscarPorId(Integer id){
        return notaItemJuncaoRepository.findById(id)
                .map(this::toDTO);
    }

    public NotaItemJuncaoDTO salvarJuncao(NotaItemJuncao notaItemJuncao,
                                          Integer idNota,
                                          Integer idItem){

        Nota nota = notaRepository.findById(idNota)
                    .orElseThrow(()->new RuntimeException("Nota não encontrada"));

        Item item = itemRepository.findById(idItem)
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        notaItemJuncao.setNota(nota);
        notaItemJuncao.setItem(item);

        notaItemJuncao.getNota().getNotaItemJuncaos().add(notaItemJuncao);
        notaItemJuncao.getItem().getNotaItemJuncaos().add(notaItemJuncao);

        return toDTO(notaItemJuncaoRepository.save(notaItemJuncao));
    }

    public void deletarPorId(Integer id){
        notaItemJuncaoRepository.deleteById(id);
    }

}

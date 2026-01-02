package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.NotaItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.ItemRepository;
import com.controle_de_gastos.notas_api.repository.NotaItemJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaItemRespostaDTO;
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

    public NotaItemRespostaDTO toRespostaDTO(NotaItemJuncao notaItemJuncao) {
        NotaRespostaDTO notaRespostaDTO = notaService.toRespostaDTO(notaItemJuncao.getNota());
        ItemRespostaDTO itemRespostaDTO = itemService.toRespostaDTO(notaItemJuncao.getItem());


        return new NotaItemRespostaDTO(
                    notaItemJuncao.getId(),
                    notaItemJuncao.getQuantidade(),
                    notaItemJuncao.getValorUnitario(),
                notaRespostaDTO,
                itemRespostaDTO
            );
    }

    public List<NotaItemRespostaDTO> listarTodos(){
        return notaItemJuncaoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<NotaItemRespostaDTO> buscarPorId(Integer id){
        return notaItemJuncaoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public NotaItemRespostaDTO salvarJuncao(NotaItemRequisicaoDTO notaItemDTO){

        Nota nota = notaRepository.findById(notaItemDTO.idNota())
                    .orElseThrow(()->new RuntimeException("Nota não encontrada"));

        Item item = itemRepository.findById(notaItemDTO.idItem())
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        NotaItemJuncao notaItemJuncao = new NotaItemJuncao();

        notaItemJuncao.setNota(nota);
        notaItemJuncao.setItem(item);

        notaItemJuncao.getNota().getNotaItemJuncaos().add(notaItemJuncao);
        notaItemJuncao.getItem().getNotaItemJuncaos().add(notaItemJuncao);

        return toRespostaDTO(notaItemJuncaoRepository.save(notaItemJuncao));
    }

    public void deletarPorId(Integer id){
        notaItemJuncaoRepository.deleteById(id);
    }

}

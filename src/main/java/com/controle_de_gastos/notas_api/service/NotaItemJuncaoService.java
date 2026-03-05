package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.projecao.ItemSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;
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
        return new NotaItemRespostaDTO(
                    notaItemJuncao.getId(),
                    notaItemJuncao.getQuantidade(),
                    notaItemJuncao.getValorUnitario(),
                new NotaSimplesProjecaoDTO(
                        notaItemJuncao.getNota().getId(),
                        notaItemJuncao.getNota().getData(),
                        notaItemJuncao.getNota().getTotal()
                ),
                new ItemSimplesProjecaoDTO(
                        notaItemJuncao.getId(),
                        notaItemJuncao.getItem().getNome(),
                        notaItemJuncao.getItem().getPeso(),
                        notaItemJuncao.getItem().getVersao()
                )
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

    public NotaItemRespostaDTO criar(NotaItemRequisicaoDTO notaItemDTO){

        Nota nota = notaRepository.findById(notaItemDTO.idNota())
                    .orElseThrow(()->new RuntimeException("Nota não encontrada"));

        Item item = itemRepository.findById(notaItemDTO.idItem())
                .orElseThrow(()->new RuntimeException("Item não encontrado"));

        NotaItemJuncao notaItemJuncao = new NotaItemJuncao();

        notaItemJuncao.setNota(nota);
        notaItemJuncao.setItem(item);
        notaItemJuncao.setQuantidade(notaItemDTO.quantidade());
        notaItemJuncao.setValorUnitario(notaItemDTO.valorUnitario());

        notaItemJuncao.getNota().getNotaItemJuncaos().add(notaItemJuncao);
        notaItemJuncao.getItem().getNotaItemJuncaos().add(notaItemJuncao);

        return toRespostaDTO(notaItemJuncaoRepository.save(notaItemJuncao));
    }

    public boolean desassociar(Integer id){
        Optional<NotaItemJuncao> notaItemJuncao = notaItemJuncaoRepository.findById(id);

        if (notaItemJuncao.isEmpty()) return false;

        notaItemJuncaoRepository.deleteById(id);
        return true;
    }

}

package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.projecao.ItemSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemTipoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemTipoComItemRespostaDTO;
import com.controle_de_gastos.notas_api.model.Item;
import com.controle_de_gastos.notas_api.repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemTipoRespostaDTO;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemTipoService {

    private final ItemTipoRepository itemTipoRepository;

    public ItemTipoRespostaDTO toRespostaDTO(ItemTipo itemTipo){
        return new ItemTipoRespostaDTO(
                itemTipo.getId(),
                itemTipo.getNome()
        );
    }

    public List<ItemTipoRespostaDTO> listarTodos(){
        return itemTipoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<ItemTipoRespostaDTO> buscarPorId(Integer id){
        return itemTipoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public ItemTipoRespostaDTO criar(ItemTipoRequisicaoDTO tipoDTO){
        ItemTipo tipo = ItemTipo.builder()
                .nome(tipoDTO.nome())
                .build();
       return toRespostaDTO(itemTipoRepository.save(tipo));
    }

    public Optional<ItemTipoRespostaDTO> atualizarTudo(Integer id, ItemTipoRequisicaoDTO tipoDTO){
        return itemTipoRepository.findById(id)
                .map(tipo -> {
                    tipo.setNome(tipoDTO.nome());
                    return toRespostaDTO(itemTipoRepository.save(tipo));
                });
    }

    public List<ItemTipoComItemRespostaDTO> listarItensPorItemTipoTodos(){
        return itemTipoRepository.findAll()
                .stream()
                .map(tipo ->{
                    return new ItemTipoComItemRespostaDTO(
                       tipo.getId(),
                       tipo.getNome(),
                       tipo.getItens()
                               .stream()
                               .map(item->{
                                   return new ItemSimplesProjecaoDTO(
                                           item.getId(),
                                           item.getNome(),
                                           item.getPeso(),
                                           item.getVersao()
                                   );
                                       }).toList()
               );
                })
                .toList();

    }

    public Optional<ItemTipoComItemRespostaDTO> listarItensPorItemTipoId(Integer id){
        return itemTipoRepository.findById(id)
                .map(tipo -> {
                   return new ItemTipoComItemRespostaDTO(
                            tipo.getId(),
                            tipo.getNome(),
                            tipo.getItens()
                                    .stream()
                                    .map(item ->{
                                        return new ItemSimplesProjecaoDTO(
                                                item.getId(),
                                                item.getNome(),
                                                item.getPeso(),
                                                item.getVersao()
                                        );
                                    })
                                    .toList()
                    );
                });
    }


    public boolean deletarPorId(Integer id){
        Optional<ItemTipo> itemTipo = itemTipoRepository.findById(id);
        if(itemTipo.isEmpty()) return false;

        if (!itemTipo.get().getItens().isEmpty()) throw new IllegalStateException("Não é possível deletar, existem dependências");

        itemTipoRepository.deleteById(id);
        return true;
    }

}

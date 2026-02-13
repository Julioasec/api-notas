package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemTipoRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ItemTipoRespostaDTO;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        ItemTipo tipo = new ItemTipo();
        tipo.setNome(tipoDTO.nome());
       return toRespostaDTO(itemTipoRepository.save(tipo));
    }

    public boolean deletarPorId(Integer id){
        Optional<ItemTipo> itemTipo = itemTipoRepository.findById(id);
        if(itemTipo.isEmpty()) return false;

        if (!itemTipo.get().getItens().isEmpty()) throw new IllegalStateException("Não é possível deletar, existem dependências");

        itemTipoRepository.deleteById(id);
        return true;
    }

}

package com.controle_de_gastos.notas_api.controller;


import com.controle_de_gastos.notas_api.dto.resposta.ItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributosRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<ItemRespostaDTO> listarTodos(){
        return itemService.listartodos();
    }
    @GetMapping("/{id}")
    public Optional<ItemRespostaDTO> buscarPorId(@PathVariable Integer id){
        return itemService.buscarPorId(id);
    }

    @GetMapping("/{id}/atributos")
    public List<ItemAtributosRespostaDTO> listarAtributosPorItem(@PathVariable Integer id){
        return itemService.listarAtributosPorItem(id);
    }

    @PostMapping
    public ItemRespostaDTO criar(@RequestBody ItemRequisicaoDTO itemDTO){
        return itemService.salvarItem(itemDTO);
    }

    @DeleteMapping
    public void excluirPorId(Integer id){
        itemService.deletarPorID(id);
    }

}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.ItemDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemRequisicao;
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
    public List<ItemDTO> listarTodos(){
        return itemService.listartodos();
    }
    @GetMapping("/{id}")
    public Optional<ItemDTO> buscarPorId(@PathVariable Integer id){
        return itemService.buscarPorId(id);
    }

    @PostMapping
    public ItemDTO salvarItem(@RequestBody ItemRequisicao item){
        return itemService.salvarItem(item);
    }

    @DeleteMapping
    public void excluirPorId(Integer id){
        itemService.deletarPorID(id);
    }

}

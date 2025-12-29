package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.ItemAtributoJuncaoDTO;
import com.controle_de_gastos.notas_api.model.ItemAtributoJuncao;
import com.controle_de_gastos.notas_api.service.ItemAtributoJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-atributo")
@RequiredArgsConstructor
public class ItemAtributoJuncaoController {
    private final ItemAtributoJuncaoService itemAtributoJuncaoService;


    @GetMapping
    public List<ItemAtributoJuncaoDTO> listarTodos(){
        return itemAtributoJuncaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ItemAtributoJuncaoDTO> listarPorId(@RequestParam Integer id){
        return itemAtributoJuncaoService.buscarPorid(id);
    }

    @PostMapping
    public ItemAtributoJuncaoDTO salvarJuncao(@RequestBody ItemAtributoJuncao itemAtributo){
        return itemAtributoJuncaoService.salvarAtribuicao(itemAtributo);
    }
}

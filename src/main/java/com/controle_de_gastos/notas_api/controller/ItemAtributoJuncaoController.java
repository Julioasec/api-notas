package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemAtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributoRespostaDTO;
import com.controle_de_gastos.notas_api.service.ItemAtributoJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-atributo")
@RequiredArgsConstructor
public class ItemAtributoJuncaoController {

    private final ItemAtributoJuncaoService itemAtributoJuncaoService;


    @GetMapping
    public List<ItemAtributoRespostaDTO> listarTodos(){
        return itemAtributoJuncaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ItemAtributoRespostaDTO> listarPorId(@RequestParam Integer id){
        return itemAtributoJuncaoService.buscarPorid(id);
    }

    @PostMapping
    public ItemAtributoRespostaDTO associar(@RequestBody ItemAtributoRequisicaoDTO juncao){
        return itemAtributoJuncaoService.associar(juncao);
    }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemAtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributoRespostaDTO;
import com.controle_de_gastos.notas_api.service.ItemAtributoJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/item-atributo")
@RequiredArgsConstructor
public class ItemAtributoJuncaoController {

    private final ItemAtributoJuncaoService itemAtributoJuncaoService;

    @GetMapping
    public ResponseEntity<List<ItemAtributoRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(itemAtributoJuncaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemAtributoRespostaDTO> listarPorId(@RequestParam Integer id){
        return itemAtributoJuncaoService.buscarPorid(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemAtributoRespostaDTO> associar(@RequestBody ItemAtributoRequisicaoDTO juncao){
        return ResponseEntity.status(201).body(itemAtributoJuncaoService.associar(juncao));
    }
}

//    @DeleteMapping
//    public ResponseEntity<Void> deletarPorId(@RequestParam Integer id){
//        boolean isDeletado =
//    }

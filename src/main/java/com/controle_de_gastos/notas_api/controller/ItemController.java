package com.controle_de_gastos.notas_api.controller;


import com.controle_de_gastos.notas_api.dto.resposta.ItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemAtributosRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(itemService.listartodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemRespostaDTO> buscarPorId(@PathVariable Integer id){

        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/atributos")
    public ResponseEntity<List<ItemAtributosRespostaDTO>> listarAtributosPorItem(@PathVariable Integer id){
        return ResponseEntity.ok(itemService.listarAtributosPorItem(id));
    }

    @PostMapping
    public ResponseEntity<ItemRespostaDTO> criar(@RequestBody ItemRequisicaoDTO itemDTO){
        return ResponseEntity.status(201).body(itemService.criarItem(itemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable Integer id){
        boolean isDeletado;
        try{
           isDeletado = itemService.deletarPorID(id);
        }catch(Exception ex){
            return ResponseEntity.status(409).build();
        }

        if(isDeletado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemTipoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemTipoRespostaDTO;
import com.controle_de_gastos.notas_api.service.ItemTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-tipo")
@RequiredArgsConstructor
public class ItemTipoController {

    private final ItemTipoService itemTipoService;

    @GetMapping
    public ResponseEntity<List<ItemTipoRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(itemTipoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTipoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return itemTipoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemTipoRespostaDTO> criar(@RequestBody ItemTipoRequisicaoDTO tipoDTO){
        return ResponseEntity.status(201).body(itemTipoService.criar(tipoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemTipoRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody ItemTipoRequisicaoDTO tipoDTO){
        Optional<ItemTipoRespostaDTO> atualizado = itemTipoService.atualizarTudo(id, tipoDTO);
        return atualizado
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        boolean isDeletado;
        try {
          isDeletado  = itemTipoService.deletarPorId(id);
        }catch (Exception ex){
            return ResponseEntity.status(409).build();
        }

        if (isDeletado) return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();

    }


}

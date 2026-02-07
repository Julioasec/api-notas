package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.AtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.AtributoRespostaDTO;
import com.controle_de_gastos.notas_api.service.AtributoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atributos")
@RequiredArgsConstructor
public class AtributoController {

    private final AtributoService atributoService;

    @GetMapping
    public ResponseEntity<List<AtributoRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(atributoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtributoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return atributoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtributoRespostaDTO> criar(@RequestBody AtributoRequisicaoDTO atributoDTO){
        AtributoRespostaDTO criado = atributoService.criar(atributoDTO);
        return ResponseEntity.status(201).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtributoRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody AtributoRequisicaoDTO atributoDTO){
        Optional<AtributoRespostaDTO> atualizado = atributoService.atualizarTudo(id, atributoDTO);

        return atualizado
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        boolean isDeletado;
        try {
            isDeletado = atributoService.deletarPorId(id);
        }catch (Exception ex){
            return ResponseEntity.status(409).build();
        }

        if(!isDeletado){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}

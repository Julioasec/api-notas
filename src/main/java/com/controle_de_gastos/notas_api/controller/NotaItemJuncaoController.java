package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.NotaItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaItemRespostaDTO;
import com.controle_de_gastos.notas_api.service.NotaItemJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota-item")
@RequiredArgsConstructor
public class NotaItemJuncaoController {

    private final NotaItemJuncaoService notaItemJuncaoService;

    @GetMapping
    public ResponseEntity<List<NotaItemRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(notaItemJuncaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaItemRespostaDTO> listarPorId(@PathVariable Integer id){
        return notaItemJuncaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotaItemRespostaDTO> salvarJuncao(NotaItemRequisicaoDTO notaItemDTO){
        return ResponseEntity.status(201).body(notaItemJuncaoService.salvarJuncao(notaItemDTO));
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Integer id){
        notaItemJuncaoService.deletarPorId(id);
    }

}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.BairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroComEstabRespostaDTO;
import com.controle_de_gastos.notas_api.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bairro")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    @GetMapping
    public ResponseEntity<List<BairroRespostaDTO>> listarTodos() {
        return ResponseEntity.ok(bairroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BairroRespostaDTO> buscarPorId(@PathVariable Integer id){
        return bairroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    // Corrigir
    @GetMapping("/{id}/estabelecimentos")
    public ResponseEntity<BairroComEstabRespostaDTO> listarEstabPorBairroId(@PathVariable Integer id){
        Optional<BairroComEstabRespostaDTO> resposta = bairroService.listarEstabPorBairroId(id);

        return resposta
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/estabelecimentos")
    public ResponseEntity<List<BairroComEstabRespostaDTO>> listarTodosEstabPorBairro(){
            return ResponseEntity.ok(bairroService.listarTodosEstabPorBairro());
    }

    @PostMapping
    public ResponseEntity<BairroRespostaDTO> criar(@RequestBody BairroRequisicaoDTO bairroDTO) {
        return ResponseEntity.status(201).body(bairroService.criar(bairroDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BairroRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody BairroRequisicaoDTO bairroDTO) {
        Optional<BairroRespostaDTO> atualizado = bairroService.atualizarTudo(id, bairroDTO);
        return atualizado
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBairro(@PathVariable Integer id){
        boolean isDeletado = bairroService.deletarPorId(id);

        if(isDeletado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }
}

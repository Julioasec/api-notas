package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MarcaRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MarcaComItemRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MarcaRespostaDTO;
import com.controle_de_gastos.notas_api.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<MarcaRespostaDTO>> listarMarcas(){
        return ResponseEntity.ok(marcaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaRespostaDTO> buscarMarcaPorId(@PathVariable Integer id){
        return marcaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<MarcaComItemRespostaDTO> listarItensPorMarcaId(@PathVariable Integer id){
        return marcaService.listarItensPorMarcaId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/itens")
    public ResponseEntity<List<MarcaComItemRespostaDTO>> listarItensPorMarcaTodos(){
        return ResponseEntity.ok(marcaService.listarItensPorMarcaTodos());
    }

    @PostMapping
    public ResponseEntity<MarcaRespostaDTO> criar(@RequestBody MarcaRequisicaoDTO marcaDTO) {
        return ResponseEntity.status(201).body(marcaService.criar(marcaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody MarcaRequisicaoDTO marcaDTO) {
        return marcaService.atualizarTudo(id, marcaDTO)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        boolean isDeletado;

        try {
            isDeletado = marcaService.deletarPorId(id);
        }catch (Exception ex){
            return  ResponseEntity.status(409).build();
        }

        if (isDeletado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

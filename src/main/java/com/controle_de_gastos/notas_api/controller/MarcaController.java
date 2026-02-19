package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MarcaRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MarcaRespostaDTO;
import com.controle_de_gastos.notas_api.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<MarcaRespostaDTO> criar(@RequestBody MarcaRequisicaoDTO marcaDTO) {
        return ResponseEntity.status(201).body(marcaService.criar(marcaDTO));
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        marcaService.deletarPorId(id);
    }
}

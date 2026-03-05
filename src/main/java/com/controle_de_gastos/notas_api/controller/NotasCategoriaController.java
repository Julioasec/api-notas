package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.NotasCategoriaRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotasCategoriaRespostaDTO;
import com.controle_de_gastos.notas_api.service.NotasCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas-categorias")
@RequiredArgsConstructor
public class NotasCategoriaController {


    private final NotasCategoriaService notasCategoriaService;

    @GetMapping
    public ResponseEntity<List<NotasCategoriaRespostaDTO>> listarCategorias(){
        return ResponseEntity.ok(notasCategoriaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotasCategoriaRespostaDTO> buscarPorId(@PathVariable Integer id){
        return notasCategoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<NotasCategoriaRespostaDTO> criarCategoria(@RequestBody NotasCategoriaRequisicaoDTO notasCategoriaDTO){
        return ResponseEntity.ok(notasCategoriaService.criar(notasCategoriaDTO));
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        notasCategoriaService.deletarPorId(id);
    }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.CategoriaEstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.CategoriaEstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.CategoriaEstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria-estabelecimento")
@RequiredArgsConstructor
public class CategoriaEstabelecimentoController {

    private final CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    @GetMapping
    public ResponseEntity<List<CategoriaEstabelecimentoRespostaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaEstabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEstabelecimentoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return categoriaEstabelecimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaEstabelecimentoRespostaDTO> criar(@RequestBody CategoriaEstabelecimentoRequisicaoDTO categoriaDTO) {
        return ResponseEntity.status(201).body(categoriaEstabelecimentoService.criar(categoriaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEstabelecimentoRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody CategoriaEstabelecimentoRequisicaoDTO categoriaDTO) {
        Optional<CategoriaEstabelecimentoRespostaDTO> atualizado = categoriaEstabelecimentoService.atualizarTudo(id,categoriaDTO);
        return atualizado
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id){
        boolean isDeletado;
        try{
           isDeletado = categoriaEstabelecimentoService.deletarPorId(id);
        }catch (Exception ex){
            return ResponseEntity.status(409).build();
        }

        if(isDeletado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

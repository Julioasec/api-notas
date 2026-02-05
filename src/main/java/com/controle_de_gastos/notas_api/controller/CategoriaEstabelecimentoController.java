package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.CategoriaEstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.CategoriaEstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.CategoriaEstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CategoriaEstabelecimentoRespostaDTO> listarCategorias() {
        return categoriaEstabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaEstabelecimentoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return categoriaEstabelecimentoService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaEstabelecimentoRespostaDTO criar(@RequestBody CategoriaEstabelecimentoRequisicaoDTO categoriaDTO) {
        return categoriaEstabelecimentoService.criar(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id){
        boolean isDeletado = categoriaEstabelecimentoService.deletarPorId(id);

        if(isDeletado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.CategoriaEstabelecimentoDTO;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import com.controle_de_gastos.notas_api.service.CategoriaEstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria-estabelecimento")
@RequiredArgsConstructor
public class CategoriaEstabelecimentoController {

    private final CategoriaEstabelecimentoService categoriaEstabelecimentoService;

    @GetMapping
    public List<CategoriaEstabelecimentoDTO> listarCategorias() {
        return categoriaEstabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaEstabelecimentoDTO> buscarPorId(@PathVariable Integer id){
        return categoriaEstabelecimentoService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaEstabelecimentoDTO criarCategoria(@RequestBody CategoriaEstabelecimentoDTO categoria) {
        return categoriaEstabelecimentoService.salvarCategoria(categoria);
    }

    @DeleteMapping
    public void deletarCategoria(@RequestParam Integer id){
        categoriaEstabelecimentoService.deletarPorId(id);
    }
}

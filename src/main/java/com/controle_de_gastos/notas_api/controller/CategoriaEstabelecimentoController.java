package com.controle_de_gastos.notas_api.controller;



import com.controle_de_gastos.notas_api.Repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import com.controle_de_gastos.notas_api.service.CategoriaEstabelecimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria-estabelecimento")
public class CategoriaEstabelecimentoController {

    private final CategoriaEstabelecimentoService categoriaEstabelecimentoService;
    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;

    public CategoriaEstabelecimentoController(CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository, CategoriaEstabelecimentoService categoriaEstabelecimentoService){
        this.categoriaEstabelecimentoRepository = categoriaEstabelecimentoRepository;
        this.categoriaEstabelecimentoService = categoriaEstabelecimentoService;
    }

    @GetMapping
    public List<CategoriaEstabelecimento> listarCategorias() {
        return categoriaEstabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<CategoriaEstabelecimento> buscarPorId(@PathVariable Integer id){
        return categoriaEstabelecimentoService.buscarPorId(id);
    }

    @PostMapping
    public CategoriaEstabelecimento criarCategoria(@RequestBody CategoriaEstabelecimento categoria) {
        return categoriaEstabelecimentoService.salvarCategoria(categoria);
    }

    @DeleteMapping
    public void deletarCategoria(@RequestParam Integer id){
        categoriaEstabelecimentoService.deletarPorId(id);
    }
}

package com.controle_de_gastos.notas_api.controller;



import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import com.controle_de_gastos.notas_api.service.CategoriaEstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-estabelecimento")
public class CategoriaEstabelecimentoController {
    @Autowired
    CategoriaEstabelecimentoService categoria;

    @GetMapping
    public List<CategoriaEstabelecimento> listarCategorias() {
        return categoria.listarCategorias();
    }

    @PostMapping
    public CategoriaEstabelecimento criarCategoria(@RequestBody CategoriaEstabelecimento categoria) {
        return this.categoria.criarCategoria(categoria);
    }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.model.Categoria;
import com.controle_de_gastos.notas_api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> ListarCategorias(){
        return categoriaService.ListarCategorias();
    }


    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria){
        return categoriaService.criarCategoria(categoria);
    }
}

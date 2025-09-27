package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.MarcaRepository;
import com.controle_de_gastos.notas_api.model.Marca;
import com.controle_de_gastos.notas_api.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private final MarcaService marcaService;
    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaController(MarcaService marcaService, MarcaRepository marcaRepository) {
        this.marcaService = marcaService;
        this.marcaRepository = marcaRepository;
    }

    @GetMapping
    public List<Marca> listarMarcas(){
        return marcaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Marca> buscarMarcaPorId(@PathVariable Integer id){
        return marcaService.buscarPorId(id);
    }

    @PostMapping
    public Marca salvarMarca(@RequestBody Marca marca) {
        return marcaService.salvarMarca(marca);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        marcaService.deletarPorId(id);
    }
}

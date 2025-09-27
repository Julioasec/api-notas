package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import com.controle_de_gastos.notas_api.service.NotasCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas-categorias")
public class NotasCategoriaController {

    @Autowired
    private NotasCategoriaService notasCategoriaService;
    private NotasCategoriaRepository notasCategoriaRepository;

    public NotasCategoriaController(NotasCategoriaService notasCategoriaService, NotasCategoriaRepository notasCategoriaRepository) {
        this.notasCategoriaService = notasCategoriaService;
        this.notasCategoriaRepository = notasCategoriaRepository;
    }


    @GetMapping
    public List<NotasCategoria> listarCategorias(){
        return notasCategoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotasCategoria> buscarPorId(@PathVariable Integer id){
        return notasCategoriaRepository.findById(id);
    }

    @PostMapping
    public NotasCategoria criarCategoria(@RequestBody NotasCategoria notasCategoria){
        return notasCategoriaService.salvar(notasCategoria);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        notasCategoriaRepository.deleteById(id);
    }
}

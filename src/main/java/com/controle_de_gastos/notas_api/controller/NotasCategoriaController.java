package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.NotasCategoriaRepository;
import com.controle_de_gastos.notas_api.dto.NotasCategoriaDTO;
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
    public List<NotasCategoriaDTO> listarCategorias(){
        return notasCategoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotasCategoriaDTO> buscarPorId(@PathVariable Integer id){
        return notasCategoriaRepository.findById(id)
                .map(notasCategoriaService::toDTO);
    }

    @PostMapping
    public NotasCategoriaDTO criarCategoria(@RequestBody NotasCategoria notasCategoria){
        return notasCategoriaService.salvarCategoria(notasCategoria);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        notasCategoriaRepository.deleteById(id);
    }
}

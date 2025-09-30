package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.NotasCategoriaDTO;
import com.controle_de_gastos.notas_api.model.NotasCategoria;
import com.controle_de_gastos.notas_api.service.NotasCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas-categorias")
@RequiredArgsConstructor
public class NotasCategoriaController {

    private NotasCategoriaService notasCategoriaService;

    @GetMapping
    public List<NotasCategoriaDTO> listarCategorias(){
        return notasCategoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotasCategoriaDTO> buscarPorId(@PathVariable Integer id){
        return notasCategoriaService.buscarPorId(id);

    }

    @PostMapping
    public NotasCategoriaDTO criarCategoria(@RequestBody NotasCategoria notasCategoria){
        return notasCategoriaService.salvarCategoria(notasCategoria);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        notasCategoriaService.deletarPorId(id);
    }
}

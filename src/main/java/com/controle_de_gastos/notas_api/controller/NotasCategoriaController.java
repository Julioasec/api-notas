package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.NotasCategoriaRespostaDTO;
import com.controle_de_gastos.notas_api.service.NotasCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas-categorias")
@RequiredArgsConstructor
public class NotasCategoriaController {

    @Autowired
    private NotasCategoriaService notasCategoriaService;

    @GetMapping
    public List<NotasCategoriaRespostaDTO> listarCategorias(){
        return notasCategoriaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotasCategoriaRespostaDTO> buscarPorId(@PathVariable Integer id){
        return notasCategoriaService.buscarPorId(id);

    }

    @PostMapping
    public NotasCategoriaRespostaDTO criarCategoria(@RequestBody NotasCategoriaRespostaDTO notasCategoriaRespostaDTO){
        return notasCategoriaService.criar(notasCategoriaRespostaDTO);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        notasCategoriaService.deletarPorId(id);
    }
}

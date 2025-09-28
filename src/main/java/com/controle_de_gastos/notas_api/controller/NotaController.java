package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota")
@RequiredArgsConstructor
public class NotaController {
    private final NotaRepository notaRepository;
    private final NotaService notaService;

    @GetMapping
    public List<NotaDTO> listarTodos(){
        return notaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotaDTO> buscarPorId(@PathVariable Integer id){
        return notaService.buscarPorid(id);
    }

    @PostMapping
    public Nota salvarNota(@RequestBody Nota nota,
                           @RequestParam(name = "idCategoria") Integer idCategoria,
                           @RequestParam(name = "idEstabelecimento") Integer idEstabelecimento){
        return notaService.salvarNota(nota, idCategoria, idEstabelecimento);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        notaService.deletarPorId(id);
    }
}

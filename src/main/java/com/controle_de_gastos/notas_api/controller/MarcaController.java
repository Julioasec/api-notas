package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MarcaRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MarcaRespostaDTO;
import com.controle_de_gastos.notas_api.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public List<MarcaRespostaDTO> listarMarcas(){
        return marcaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<MarcaRespostaDTO> buscarMarcaPorId(@PathVariable Integer id){
        return marcaService.buscarPorId(id);
    }

    @PostMapping
    public MarcaRespostaDTO criar(@RequestBody MarcaRequisicaoDTO marcaDTO) {
        return marcaService.criar(marcaDTO);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        marcaService.deletarPorId(id);
    }
}

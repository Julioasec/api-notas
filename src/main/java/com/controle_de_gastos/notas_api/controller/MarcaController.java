package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.MarcaDTO;
import com.controle_de_gastos.notas_api.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<MarcaDTO> listarMarcas(){
        return marcaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<MarcaDTO> buscarMarcaPorId(@PathVariable Integer id){
        return marcaService.buscarPorId(id);
    }

    @PostMapping
    public MarcaDTO salvarMarca(@RequestBody MarcaDTO marcaDTO) {
        return marcaService.salvarMarca(marcaDTO);
    }

    @DeleteMapping
    public void deletarPorId(@RequestParam Integer id){
        marcaService.deletarPorId(id);
    }
}

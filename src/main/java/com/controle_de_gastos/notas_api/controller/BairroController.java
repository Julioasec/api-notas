package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.dto.BairroEstabDTO;
import com.controle_de_gastos.notas_api.dto.BairroEstabListDTO;
import com.controle_de_gastos.notas_api.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bairro")
@RequiredArgsConstructor
public class BairroController {

    @Autowired
    private BairroService bairroService;

    @GetMapping
    public List<BairroDTO> listarBairros() {
        return bairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<BairroDTO> buscarPorId(@PathVariable Integer id){
        return bairroService.buscarPorId(id);
    }

    @GetMapping("/{id}/estabelecimentos")
    public List<BairroEstabDTO> listarEstabPorBairroId(@PathVariable Integer id){
        return bairroService.listarEstabPorBairroId(id);
    }

    @GetMapping("/estabelecimentos")
    public List<BairroEstabListDTO> listarEstabPorBairro(){
        return bairroService.listarTodosEstabPorBairro();
    }

    @PostMapping
    public BairroDTO bairroSalvar(@RequestBody BairroDTO bairro) {
        return bairroService.salvar(bairro);
    }

    @DeleteMapping
    public void deletarBairro(Integer id){
        bairroService.deletarPorId(id);
    }
}

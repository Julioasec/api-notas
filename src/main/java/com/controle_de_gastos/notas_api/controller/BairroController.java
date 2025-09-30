package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bairro")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    @GetMapping
    public List<BairroDTO> listarBairros() {
        return bairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<BairroDTO> buscarPorId(@PathVariable Integer id){
        return bairroService.buscarPorId(id);
    }

    @PostMapping
    public Bairro bairroSalvar(@RequestBody Bairro bairro) {
        return bairroService.salvar(bairro);
    }

    @DeleteMapping
    public void deletarBairro(Integer id){
        bairroService.deletarPorId(id);
    }
}

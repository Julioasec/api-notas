package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.EstabelecimentoDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoService estabelecimentoService;

    @GetMapping
    public List<EstabelecimentoDTO> listarTodos(){
        return estabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> buscarPorId(@PathVariable Integer id){
            return estabelecimentoService.buscarPorId(id);
        }


    @PostMapping
    public Estabelecimento salvarEstabelecimento(@RequestBody Estabelecimento estabelecimento, Integer id_categoria){
        return estabelecimentoService.salvarEstabelecimento(estabelecimento, id_categoria);
    }

    @DeleteMapping
    public void deletarEstabelecimento(Integer id){
        estabelecimentoService.deletarPorId(id);
    }
}

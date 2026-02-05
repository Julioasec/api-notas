package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.BairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.BairroComEstabRespostaDTO;
import com.controle_de_gastos.notas_api.service.BairroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bairro")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService bairroService;

    @GetMapping
    public List<BairroRespostaDTO> listarBairros() {
        return bairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<BairroRespostaDTO> buscarPorId(@PathVariable Integer id){
        return bairroService.buscarPorId(id);
    }

    // Corrigir
    @GetMapping("/{id}/estabelecimentos")
    public BairroComEstabRespostaDTO listarEstabPorBairroId(@PathVariable Integer id){
        return bairroService.listarEstabPorBairroId(id);
    }

    @GetMapping("/estabelecimentos")
    public List<BairroComEstabRespostaDTO> listarEstabPorBairro(){
        return bairroService.listarTodosEstabPorBairro();
    }

    @PostMapping
    public BairroRespostaDTO criar(@RequestBody BairroRequisicaoDTO bairroDTO) {
        return bairroService.criar(bairroDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBairro(@PathVariable Integer id){
        boolean isDeletado = bairroService.deletarPorId(id);

        if(isDeletado){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }
}

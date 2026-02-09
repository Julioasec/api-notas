package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoBairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoBairroRespostaDTO;
import com.controle_de_gastos.notas_api.service.EstabelecimentoBairroJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimento-bairro")
@RequiredArgsConstructor
public class EstabelecimentoBairroJuncaoController {

    private final EstabelecimentoBairroJuncaoService estabelecimentoBairroService;

    @GetMapping
    public ResponseEntity<List<EstabelecimentoBairroRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(estabelecimentoBairroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoBairroRespostaDTO> buscarPorId(@PathVariable Integer id){
        return estabelecimentoBairroService.buscarPorid(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoBairroRespostaDTO> associar(@RequestBody EstabelecimentoBairroRequisicaoDTO juncao){
            return ResponseEntity.status(201).body(estabelecimentoBairroService.associar(juncao));
    }



    @DeleteMapping
    public void deletarPorId(Integer id){
        estabelecimentoBairroService.deletarPorId(id);
    }
}

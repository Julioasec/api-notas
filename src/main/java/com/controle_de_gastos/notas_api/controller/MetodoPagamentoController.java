package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.MetodoPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metodo-pagamentos")
@RequiredArgsConstructor
public class MetodoPagamentoController {

    private final MetodoPagamentoService metodoPagamentoService;
    
    @GetMapping
    public List<MetodoPagamentoRespostaDTO> listarTodos(){
        return metodoPagamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPagamentoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return metodoPagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MetodoPagamentoRespostaDTO> criar(@RequestBody MetodoPagamentoRequisicaoDTO metodoDTO){
        return ResponseEntity.status(201).body(metodoPagamentoService.criar(metodoDTO));
    }



    @DeleteMapping
    public void deletarPorId(Integer id){
        metodoPagamentoService.deletarPorId(id);
    }
}

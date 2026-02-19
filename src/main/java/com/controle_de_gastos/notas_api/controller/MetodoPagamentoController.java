package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoComNotaRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.MetodoPagamentoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @GetMapping("/{id}/notas")
    public ResponseEntity<MetodoPagamentoComNotaRespostaDTO> listarNotasPorMetodoPagamentoId(@PathVariable Integer id){
        return metodoPagamentoService.listarNotasPorMetodoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/notas")
    public ResponseEntity<List<MetodoPagamentoComNotaRespostaDTO>> listarNotasPorMetodoPagamentoTodos(){
        return ResponseEntity.ok(metodoPagamentoService.listarNotasPorMetodoPagamentoTodos());
    }

    @PostMapping
    public ResponseEntity<MetodoPagamentoRespostaDTO> criar(@RequestBody MetodoPagamentoRequisicaoDTO metodoDTO){
        return ResponseEntity.status(201).body(metodoPagamentoService.criar(metodoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagamentoRespostaDTO> atualizarTudo(@PathVariable Integer id, @RequestBody MetodoPagamentoRequisicaoDTO metodoDTO){
        return metodoPagamentoService.atualizarTudo(id, metodoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        boolean isDeletado;

        try{
            isDeletado = metodoPagamentoService.deletarPorId(id);
        }catch (Exception ex){
          return ResponseEntity.status(409).build();
        }

        if (!isDeletado) return ResponseEntity.notFound().build();

        metodoPagamentoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}

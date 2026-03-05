package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.NotaMetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotasMetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.service.NotaMetodoPagamentoJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota-metodo-pagamento")
@RequiredArgsConstructor
public class NotasMetodoPagamentoJuncaoController {

    private final NotaMetodoPagamentoJuncaoService notaMetodoPagamentoJuncaoService;


    @GetMapping
    public ResponseEntity<List<NotaMetodoPagamentoRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(notaMetodoPagamentoJuncaoService.listarTodos());

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaMetodoPagamentoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return notaMetodoPagamentoJuncaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NotaMetodoPagamentoRespostaDTO> associar(@RequestBody NotasMetodoPagamentoRequisicaoDTO notaMetodoPagamentoDTO){
       return ResponseEntity.status(201).body(notaMetodoPagamentoJuncaoService.asssociar(notaMetodoPagamentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        boolean isDeletado = notaMetodoPagamentoJuncaoService.deletarPorId(id);
        if (isDeletado){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}

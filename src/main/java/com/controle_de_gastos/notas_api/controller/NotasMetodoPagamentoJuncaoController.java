package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.NotaMetodoPagamentoJuncaoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotasMetodoPagamentoRequisicao;
import com.controle_de_gastos.notas_api.service.NotaMetodoPagamentoJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota-metodo-pagamento")
@RequiredArgsConstructor
public class NotasMetodoPagamentoJuncaoController {

    @Autowired
    private NotaMetodoPagamentoJuncaoService notaMetodoPagamentoJuncaoService;


    @GetMapping
    public List<NotaMetodoPagamentoJuncaoDTO> listarTodos(){
        return notaMetodoPagamentoJuncaoService.listarTodos();

    }

    @GetMapping("/{id}")
    public Optional<NotaMetodoPagamentoJuncaoDTO> searchPorId(@PathVariable Integer id){
        return notaMetodoPagamentoJuncaoService.buscarPorId(id);
    }

    @PostMapping
    public NotaMetodoPagamentoJuncaoDTO associar(@RequestBody NotasMetodoPagamentoRequisicao notaMetodoPagamentoRequisicao){
       return notaMetodoPagamentoJuncaoService.asssociar(notaMetodoPagamentoRequisicao);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Integer id){
        notaMetodoPagamentoJuncaoService.deletarPorId(id);
    }

}

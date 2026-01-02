package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.NotaMetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotasMetodoPagamentoRequisicaoDTO;
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

    private final NotaMetodoPagamentoJuncaoService notaMetodoPagamentoJuncaoService;


    @GetMapping
    public List<NotaMetodoPagamentoRespostaDTO> listarTodos(){
        return notaMetodoPagamentoJuncaoService.listarTodos();

    }

    @GetMapping("/{id}")
    public Optional<NotaMetodoPagamentoRespostaDTO> searchPorId(@PathVariable Integer id){
        return notaMetodoPagamentoJuncaoService.buscarPorId(id);
    }

    @PostMapping
    public NotaMetodoPagamentoRespostaDTO associar(@RequestBody NotasMetodoPagamentoRequisicaoDTO notaMetodoPagamentoDTO){
       return notaMetodoPagamentoJuncaoService.asssociar(notaMetodoPagamentoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Integer id){
        notaMetodoPagamentoJuncaoService.deletarPorId(id);
    }

}

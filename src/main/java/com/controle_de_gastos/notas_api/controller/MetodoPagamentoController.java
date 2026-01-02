package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.MetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.MetodoPagamentoService;
import lombok.RequiredArgsConstructor;
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
    public Optional<MetodoPagamentoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return metodoPagamentoService.buscarPorId(id);
    }

    @PostMapping
    public MetodoPagamentoRespostaDTO criar(@RequestBody MetodoPagamentoRequisicaoDTO metodoDTO){
        return metodoPagamentoService.criar(metodoDTO);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        metodoPagamentoService.deletarPorId(id);
    }
}

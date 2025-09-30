package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.MetodoPagamentoDTO;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import com.controle_de_gastos.notas_api.service.MetodoPagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metodo-pagamentos")
public class MetodoPagamentoController {

    private final MetodoPagamentoService metodoPagamentoService;

    public MetodoPagamentoController(MetodoPagamentoService metodoPagamentoService) {
            this.metodoPagamentoService = metodoPagamentoService;
    }

    @GetMapping
    public List<MetodoPagamentoDTO> listarTodos(){
        return metodoPagamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<MetodoPagamentoDTO> buscarPorId(@PathVariable Integer id){
        return metodoPagamentoService.buscarPorId(id);
    }

    @PostMapping
    public MetodoPagamentoDTO salvarMetodo(@RequestBody MetodoPagamento metodoPagamento){
        return metodoPagamentoService.salvarMetodo(metodoPagamento);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        metodoPagamentoService.deletarPorId(id);
    }
}

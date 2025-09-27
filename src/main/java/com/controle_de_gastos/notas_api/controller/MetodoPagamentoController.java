package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import com.controle_de_gastos.notas_api.service.MetodoPagamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metodo-pagamentos")
public class MetodoPagamentoController {

    private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final MetodoPagamentoService metodoPagamentoService;

    public MetodoPagamentoController(MetodoPagamentoRepository metodoPagamentoRepository, MetodoPagamentoService metodoPagamentoService) {
            this.metodoPagamentoRepository = metodoPagamentoRepository;
            this.metodoPagamentoService = metodoPagamentoService;
    }

    @GetMapping
    public List<MetodoPagamento> listarTodos(){
        return metodoPagamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MetodoPagamento> buscarPorId(@PathVariable Integer id){
        return metodoPagamentoRepository.findById(id);
    }

    @PostMapping
    public MetodoPagamento salvarMetodo(@RequestBody MetodoPagamento metodoPagamento){
        return metodoPagamentoRepository.save(metodoPagamento);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        metodoPagamentoRepository.deleteById(id);
    }
}

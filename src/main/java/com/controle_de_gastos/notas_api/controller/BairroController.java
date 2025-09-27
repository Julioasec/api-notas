package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bairro")
public class BairroController {

    private final BairroService bairroService;
    private final BairroRepository bairroRepository;

    @Autowired
    public BairroController(BairroRepository bairroRepository, BairroService bairroService) {
        this.bairroService = bairroService;
        this.bairroRepository = bairroRepository;
    }

    @GetMapping
    public List<Bairro> listarBairros() {
        return bairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Bairro> buscarPorId(@PathVariable Integer id){
        return bairroService.buscarPorId(id);
    }

    @PostMapping
    public Bairro bairroSalvar(@RequestBody Bairro bairro) {
        return bairroService.salvar(bairro);
    }

    @DeleteMapping
    public void deletarBairro(Integer id){
        bairroRepository.deleteById(id);
    }
}

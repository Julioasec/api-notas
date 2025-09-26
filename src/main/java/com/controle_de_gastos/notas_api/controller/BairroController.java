package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.service.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bairro")
public class BairroController {
    @Autowired
    BairroService bairroService;

    @GetMapping
    public List<Bairro> listarBairros() {
        return bairroService.listarBairros();
    }

    @PostMapping
    public Bairro criarBairro(@RequestBody Bairro bairro) {
        return bairroService.criarBairro(bairro);
    }
}

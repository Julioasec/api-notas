package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.model.Marca;
import com.controle_de_gastos.notas_api.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> listarMarcas(){
        return marcaService.listarMarcas();
    }

    @PostMapping
    public Marca adicionarMarca(@RequestBody Marca marca){
        return marcaService.criarMarca(marca);
    }

}

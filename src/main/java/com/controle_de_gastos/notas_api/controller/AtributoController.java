package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.model.Atributo;
import com.controle_de_gastos.notas_api.service.AtributoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atributos")
public class AtributoController {

    @Autowired
    private AtributoService atributoService;

    @GetMapping
    public List<Atributo> listarAtributos(){
        return atributoService.listarAtributos();
    }

    @PostMapping
    public Atributo criarAtributo(@RequestBody Atributo atributo){
        return atributoService.criarAtributo(atributo);
    }

}

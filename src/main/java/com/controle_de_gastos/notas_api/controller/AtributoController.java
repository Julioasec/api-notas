package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.AtributoDTO;
import com.controle_de_gastos.notas_api.service.AtributoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atributos")
@RequiredArgsConstructor
public class AtributoController {

    @Autowired
    private AtributoService atributoService;

    @GetMapping
    public List<AtributoDTO> listarAtributos(){
        return atributoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<AtributoDTO> buscarPorId(@PathVariable Integer id){
        return atributoService.buscarPorId(id);
    }

    @PostMapping
    public AtributoDTO atributoSalvar(@RequestBody AtributoDTO atributo){
        return atributoService.salvar(atributo);
    }

    @DeleteMapping
    public void deletarAtributo(Integer id){
         atributoService.deletarPorId(id);
    }

}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.AtributoRepository;
import com.controle_de_gastos.notas_api.dto.AtributoDTO;
import com.controle_de_gastos.notas_api.model.Atributo;
import com.controle_de_gastos.notas_api.service.AtributoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atributos")
@RequiredArgsConstructor
public class AtributoController {


    private final AtributoService atributoService;
    private final AtributoRepository atributoRepository;

    @GetMapping
    public List<AtributoDTO> listarAtributos(){
        return atributoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<AtributoDTO> buscarPorId(@PathVariable Integer id){
        return atributoService.buscarPorId(id);
    }

    @PostMapping
    public Atributo atributoSalvar(@RequestBody Atributo atributo){
        return atributoService.salvar(atributo);
    }

    @DeleteMapping
    public void deletarAtributo(Integer id){
         atributoService.deletarPorId(id);
    }

}

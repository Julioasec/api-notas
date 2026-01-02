package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.AtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.AtributoRespostaDTO;
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

    @GetMapping
    public List<AtributoRespostaDTO> listarAtributos(){
        return atributoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<AtributoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return atributoService.buscarPorId(id);
    }

    @PostMapping
    public AtributoRespostaDTO criar(@RequestBody AtributoRequisicaoDTO atributoDTO){
        return atributoService.criar(atributoDTO);
    }

    @DeleteMapping
    public void deletarAtributo(Integer id){
         atributoService.deletarPorId(id);
    }

}

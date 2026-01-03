package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoBairroRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoBairroRespostaDTO;
import com.controle_de_gastos.notas_api.service.EstabelecimentoBairroJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimento-bairro")
@RequiredArgsConstructor
public class EstabelecimentoBairroJuncaoController {

    private final EstabelecimentoBairroJuncaoService estabelecimentoBairroService;

    @GetMapping
    public List<EstabelecimentoBairroRespostaDTO> listarTodos(){
        return estabelecimentoBairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<EstabelecimentoBairroRespostaDTO> buscarPorId(@PathVariable Integer id){
        return estabelecimentoBairroService.buscarPorid(id);
    }

    @PostMapping
    public EstabelecimentoBairroRespostaDTO associar(@RequestBody EstabelecimentoBairroRequisicaoDTO juncao){
            return estabelecimentoBairroService.associar(juncao);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        estabelecimentoBairroService.deletarPorId(id);
    }
}

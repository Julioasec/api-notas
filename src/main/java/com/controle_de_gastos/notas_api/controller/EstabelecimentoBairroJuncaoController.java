package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoBairroRequisicao;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoBairroJuncaoDTO;
import com.controle_de_gastos.notas_api.service.EstabelecimentoBairroJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimento-bairro")
@RequiredArgsConstructor
public class EstabelecimentoBairroJuncaoController {
    private final EstabelecimentoBairroJuncaoService estabelecimentoBairroService;

    @GetMapping
    public List<EstabelecimentoBairroJuncaoDTO> listarTodos(){
        return estabelecimentoBairroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<EstabelecimentoBairroJuncaoDTO> buscarPorId(@PathVariable Integer id){
        return estabelecimentoBairroService.buscarPorid(id);
    }

    @PostMapping
    public EstabelecimentoBairroJuncaoDTO associar(@RequestBody EstabelecimentoBairroRequisicao associacao){
            return estabelecimentoBairroService.associar(
                    associacao.getEstabelecimentoId(),
                    associacao.getBairroId(),
                    associacao.getEndereco()
            );
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        estabelecimentoBairroService.deletarPorId(id);
    }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.EstabelecimentoBairroDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoRequisicao;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {
    private final EstabelecimentoService estabelecimentoService;

    @GetMapping
    public List<EstabelecimentoDTO> listarTodos(){
        return estabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Estabelecimento> buscarPorId(@PathVariable Integer id){
            return estabelecimentoService.buscarPorId(id);
        }

    @GetMapping("{id}/bairros")
    public EstabelecimentoBairroDTO listarBairroPorEstabelecimentoId(@PathVariable Integer id){
        return estabelecimentoService.listarBairroPorEstabelecimentoId(id);
    }

    @GetMapping("/bairros")
    public List<EstabelecimentoBairroDTO> listarTodosBairrosPorEstabelecimentos(){
        return estabelecimentoService.listarTodosBairroPorEstabelecimento();
    }

    @PostMapping
    public EstabelecimentoDTO salvarEstabelecimento(@RequestBody EstabelecimentoRequisicao estabelecimentoReq){
        return estabelecimentoService.salvarEstabelecimento(estabelecimentoReq);
    }

    @DeleteMapping
    public void deletarEstabelecimento(Integer id){
        estabelecimentoService.deletarPorId(id);
    }
}

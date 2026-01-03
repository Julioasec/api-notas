package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoComBairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.model.Estabelecimento;
import com.controle_de_gastos.notas_api.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @GetMapping
    public List<EstabelecimentoRespostaDTO> listarTodos(){
        return estabelecimentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<EstabelecimentoRespostaDTO> buscarPorId(@PathVariable Integer id){
            return estabelecimentoService.buscarPorId(id);
        }

    @GetMapping("{id}/bairros")
    public EstabelecimentoComBairroRespostaDTO listarBairroPorEstabelecimentoId(@PathVariable Integer id){
        return estabelecimentoService.listarBairroPorEstabelecimentoId(id);
    }

    @GetMapping("/bairros")
    public List<EstabelecimentoComBairroRespostaDTO> listarTodosBairrosPorEstabelecimentos(){
        return estabelecimentoService.listarTodosBairroPorEstabelecimento();
    }

    @PostMapping
    public EstabelecimentoRespostaDTO criar(@RequestBody EstabelecimentoRequisicaoDTO estabelecimentoDTO){
        return estabelecimentoService.criar(estabelecimentoDTO);
    }

    @DeleteMapping
    public void deletarEstabelecimento(Integer id){
        estabelecimentoService.deletarPorId(id);
    }
}

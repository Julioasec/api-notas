package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoComBairroRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.EstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.EstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.service.EstabelecimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estabelecimentos")
@RequiredArgsConstructor
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<List<EstabelecimentoRespostaDTO>> listarTodos(){
        return ResponseEntity.ok(estabelecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoRespostaDTO> buscarPorId(@PathVariable Integer id){
            return estabelecimentoService.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(()->ResponseEntity.notFound().build());
        }

    @GetMapping("{id}/bairros")
    public ResponseEntity<EstabelecimentoComBairroRespostaDTO> listarBairroPorEstabelecimentoId(@PathVariable Integer id){
        return estabelecimentoService.listarBairroPorEstabelecimentoId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/bairros")
    public ResponseEntity<List<EstabelecimentoComBairroRespostaDTO>> listarTodosBairrosPorEstabelecimentos(){
        return ResponseEntity.ok(estabelecimentoService.listarTodosBairroPorEstabelecimento());
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoRespostaDTO> criar(@RequestBody EstabelecimentoRequisicaoDTO estabelecimentoDTO){
        return ResponseEntity.ok(estabelecimentoService.criar(estabelecimentoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstabelecimento(@PathVariable Integer id){
        boolean isDeletado;

        try {
            isDeletado = estabelecimentoService.deletarPorId(id);
        }catch (Exception ex){
            return ResponseEntity.status(409).build();
        }

        if (isDeletado){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }
}

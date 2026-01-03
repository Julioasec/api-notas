package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.NotaItemRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaItemRespostaDTO;
import com.controle_de_gastos.notas_api.service.NotaItemJuncaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota-item")
@RequiredArgsConstructor
public class NotaItemJuncaoController {

    private final NotaItemJuncaoService notaItemJuncaoService;

    @GetMapping
    public List<NotaItemRespostaDTO> listarTodos(){
        return notaItemJuncaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotaItemRespostaDTO> listarPorId(@PathVariable Integer id){
        return notaItemJuncaoService.buscarPorId(id);
    }

    @PostMapping
    public NotaItemRespostaDTO salvarJuncao(NotaItemRequisicaoDTO notaItemDTO){
        return notaItemJuncaoService.salvarJuncao(notaItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Integer id){
        notaItemJuncaoService.deletarPorId(id);
    }

}

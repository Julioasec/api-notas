package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.NotaItemJuncaoDTO;
import com.controle_de_gastos.notas_api.model.NotaItemJuncao;
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
    public List<NotaItemJuncaoDTO> listarTodos(){
        return notaItemJuncaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotaItemJuncaoDTO> listarPorId(@PathVariable Integer id){
        return notaItemJuncaoService.buscarPorId(id);
    }

    @PostMapping
    public NotaItemJuncaoDTO salvarJuncao(@RequestBody NotaItemJuncao notaItemJuncao,
                                          @RequestParam(name = "idNota") Integer idNota,
                                          @RequestParam(name = "idItem") Integer idItem
                                          ){
        return notaItemJuncaoService.salvarJuncao(notaItemJuncao, idNota, idItem);
    }

    @DeleteMapping("/{id}")
    public void deletarPorId(@PathVariable Integer id){
        notaItemJuncaoService.deletarPorId(id);
    }

}

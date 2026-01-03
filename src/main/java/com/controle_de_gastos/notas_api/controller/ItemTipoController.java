package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.requisicao.ItemTipoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ItemTipoRespostaDTO;
import com.controle_de_gastos.notas_api.service.ItemTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-tipo")
@RequiredArgsConstructor
public class ItemTipoController {

    private final ItemTipoService itemTipoService;


    @GetMapping
    public List<ItemTipoRespostaDTO> listarTodos(){
        return this.itemTipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ItemTipoRespostaDTO> buscarPorId(@PathVariable Integer id){
        return this.itemTipoService.buscarPorId(id);
    }

    @PostMapping
    public ItemTipoRespostaDTO criar(@RequestBody ItemTipoRequisicaoDTO tipoDTO){
        return this.itemTipoService.criar(tipoDTO);
    }
}

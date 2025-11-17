package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.ItemTipoDTO;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.service.ItemTipoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-tipo")
@RequiredArgsConstructor
public class ItemTipoController {
    private final ItemTipoService itemTipoService;


    @GetMapping
    public List<ItemTipoDTO> listarTodos(){
        return this.itemTipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ItemTipoDTO> buscarPorId(@PathVariable Integer id){
        return this.itemTipoService.buscarPorId(id);
    }

    @PostMapping
    public ItemTipoDTO salvarTipo(@RequestBody ItemTipoDTO itemTipo){
        return this.itemTipoService.salvarTipo(itemTipo);
    }
}

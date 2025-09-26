package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.service.ItemTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-tipo")
public class ItemTipoController {

    @Autowired
    private ItemTipoService itemTipoService;

    @GetMapping
    public List<ItemTipo> listarTipos(){
        return this.itemTipoService.listarTipos();
    }

    @PostMapping
    public ItemTipo criarTipo(@RequestBody ItemTipo itemTipo){
        return this.itemTipoService.criarTipo(itemTipo);
    }
}

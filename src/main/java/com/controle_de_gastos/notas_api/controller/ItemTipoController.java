package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.ItemTipoRepository;
import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.service.ItemTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item-tipo")
public class ItemTipoController {

    private ItemTipoRepository itemTipoRepository;
    private ItemTipoService itemTipoService;

    public ItemTipoController(ItemTipoRepository itemTipoRepository, ItemTipoService itemTipoService) {
        this.itemTipoRepository = itemTipoRepository;
        this.itemTipoService = itemTipoService;
    }

    @GetMapping
    public List<ItemTipo> listarTodos(){
        return this.itemTipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ItemTipo> buscarPorId(@PathVariable Integer id){
        return this.itemTipoService.buscarPorId(id);
    }

    @PostMapping
    public ItemTipo salvarTipo(@RequestBody ItemTipo itemTipo){
        return this.itemTipoService.salvarTipo(itemTipo);
    }
}

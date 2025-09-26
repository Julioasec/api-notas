package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.model.ItemTipo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemTipoService {

    private List<ItemTipo> itemTipos = new ArrayList<>();

    public ItemTipo criarTipo(ItemTipo tipo){
        tipo.setIdTipo((long) itemTipos.size() +1);
        itemTipos.add(tipo);
        return tipo;
    }

    public List<ItemTipo> listarTipos(){
        return this.itemTipos;
    }


}

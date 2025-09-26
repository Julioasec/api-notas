package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.model.Atributo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtributoService {
    private List<Atributo> atributos = new ArrayList<>();

    public List<Atributo> listarAtributos(){
        return this.atributos;
    }

    public Atributo criarAtributo(Atributo atributo){
        atributo.setIdAtributo((long) atributos.size()+1);
        atributos.add(atributo);
        return atributo;
    }
}

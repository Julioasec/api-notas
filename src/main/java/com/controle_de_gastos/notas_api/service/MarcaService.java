package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.model.Marca;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaService {

    private List<Marca> marcas = new ArrayList<>();
    public Marca criarMarca(Marca marca){
        marca.setIdMarca((long) marcas.size() +1);
        marcas.add(marca);
        return marca;
    }

    public List<Marca> listarMarcas(){
        return marcas;
    }

}

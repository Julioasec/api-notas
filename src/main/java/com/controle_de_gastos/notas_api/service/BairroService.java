package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.model.Bairro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BairroService {
    private List<Bairro> bairros = new ArrayList<>();


    public Bairro criarBairro(Bairro bairro){
        bairro.setId_bairro((long) bairros.size()+1);
        bairros.add(bairro);
        return bairro;
    }

    public List<Bairro> listarBairros() {
        return bairros;
    }
}

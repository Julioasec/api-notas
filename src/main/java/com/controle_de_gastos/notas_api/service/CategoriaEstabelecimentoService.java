package com.controle_de_gastos.notas_api.service;


import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaEstabelecimentoService {
    private List<CategoriaEstabelecimento> categorias = new ArrayList<>();

    public CategoriaEstabelecimento criarCategoria(CategoriaEstabelecimento categoria) {
        categoria.setIdCategoriaEstabelecimento((long) categorias.size() +1);
        categorias.add(categoria);
        return categoria;
    }

    public List<CategoriaEstabelecimento> listarCategorias() {
        return categorias;
    }
}

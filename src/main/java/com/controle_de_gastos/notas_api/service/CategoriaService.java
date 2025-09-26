package com.controle_de_gastos.notas_api.service;


import com.controle_de_gastos.notas_api.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    private List<Categoria> categorias = new ArrayList<>();

    public List<Categoria> listarCategorias(){
        return this.categorias;
    }

    public Categoria criarCategoria(Categoria categoria){
        categoria.setIdCategoria((long)categorias.size() +1);
        categorias.add(categoria);
        return categoria;
    }
}

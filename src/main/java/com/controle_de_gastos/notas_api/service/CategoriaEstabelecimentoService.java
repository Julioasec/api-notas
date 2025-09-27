package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.Repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaEstabelecimentoService {
    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;

    public CategoriaEstabelecimentoService(CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository){
        this.categoriaEstabelecimentoRepository = categoriaEstabelecimentoRepository;
    }

    public List<CategoriaEstabelecimento> listarTodos(){
        return categoriaEstabelecimentoRepository.findAll();
    }

    public Optional<CategoriaEstabelecimento> buscarPorId(Integer id){
        return categoriaEstabelecimentoRepository.findById(id);
    }

    public CategoriaEstabelecimento salvarCategoria(CategoriaEstabelecimento categoria) {
        return categoriaEstabelecimentoRepository.save(categoria);
    }

    public void deletarPorId(Integer id){
        categoriaEstabelecimentoRepository.deleteById(id);
    }


}

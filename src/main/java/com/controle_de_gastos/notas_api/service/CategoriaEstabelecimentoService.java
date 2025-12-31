package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.Repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.CategoriaEstabelecimentoDTO;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaEstabelecimentoService {
    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;

    public CategoriaEstabelecimentoDTO toDTO(CategoriaEstabelecimento categoriaEstabelecimento) {
        return new CategoriaEstabelecimentoDTO(
                categoriaEstabelecimento.getId(),
                categoriaEstabelecimento.getNome()
        );
    }

    public List<CategoriaEstabelecimentoDTO> listarTodos(){
        return categoriaEstabelecimentoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<CategoriaEstabelecimentoDTO> buscarPorId(Integer id){
        return categoriaEstabelecimentoRepository.findById(id)
                .map(this::toDTO);
    }

    public CategoriaEstabelecimentoDTO salvarCategoria(CategoriaEstabelecimentoDTO categoriaDTO) {
        CategoriaEstabelecimento categoria = new CategoriaEstabelecimento();
        categoria.setNome(categoriaDTO.nome());
        return toDTO(categoriaEstabelecimentoRepository.save(categoria));
    }

    public void deletarPorId(Integer id){
        categoriaEstabelecimentoRepository.deleteById(id);
    }


}

package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.AtributoRepository;
import com.controle_de_gastos.notas_api.dto.AtributoDTO;
import com.controle_de_gastos.notas_api.model.Atributo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtributoService {
    private final AtributoRepository atributoRepository;


    public AtributoDTO toDTO(Atributo atributo){
        return new AtributoDTO(
                atributo.getIdAtributo(),
                atributo.getNome()
        );
    }

    public List<AtributoDTO> listarTodos() {
        return atributoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<AtributoDTO> buscarPorId(Integer id) {
        return atributoRepository.findById(id)
                .map(this::toDTO);
    }

    public AtributoDTO salvar(AtributoDTO atributoDTO){
        Atributo atributo = new Atributo();
        atributo.setNome(atributoDTO.nome());
        return toDTO(atributoRepository.save(atributo));
    }

    public void deletarPorId(Integer id){
        atributoRepository.deleteById(id);
    }
}

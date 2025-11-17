package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BairroService {
    private final BairroRepository bairroRepository;

    public BairroDTO toDTO(Bairro bairro){
            return new BairroDTO(
                    bairro.getIdBairro(),
                    bairro.getNome()
            );
    }

    public List<BairroDTO> listarTodos() {
        return bairroRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<BairroDTO> buscarPorId(Integer id){
        return bairroRepository.findById(id)
                .map(this::toDTO);
    }

    public BairroDTO salvar(Bairro bairro){
         return toDTO(bairroRepository.save(bairro));
    }

    public void  deletarPorId(Integer id){
        bairroRepository.deleteById(id);
    }

}

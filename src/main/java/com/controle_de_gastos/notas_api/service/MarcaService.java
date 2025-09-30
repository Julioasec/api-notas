package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MarcaRepository;
import com.controle_de_gastos.notas_api.dto.MarcaDTO;
import com.controle_de_gastos.notas_api.model.Marca;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaDTO toDTO(Marca marca){
        return new MarcaDTO(
                marca.getIdMarca(),
                marca.getNome()
        );
    }

    public List<MarcaDTO> listarTodos(){
        return marcaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<MarcaDTO> buscarPorId(Integer idMarca){
        return marcaRepository.findById(idMarca)
                .map(this::toDTO);
    }

    public MarcaDTO salvarMarca(Marca marca){
        return toDTO(marcaRepository.save(marca));
    }

    public void deletarPorId(Integer idMarca){
        marcaRepository.deleteById(idMarca);
    }
}

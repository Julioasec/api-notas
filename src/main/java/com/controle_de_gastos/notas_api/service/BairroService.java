package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BairroService {
    private final BairroRepository bairroRepository;

    public BairroDTO toDTO(Bairro bairro){
            return new BairroDTO(
                    bairro.getIdBairro(),
                    bairro.getNome()
            );
    }

    public BairroService(BairroRepository bairroRepository){
        this.bairroRepository = bairroRepository;
    };

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

    public Bairro salvar(Bairro bairro){
         return bairroRepository.save(bairro);
    }

    public void  deletarPorId(Integer id){
        bairroRepository.deleteById(id);
    }

}

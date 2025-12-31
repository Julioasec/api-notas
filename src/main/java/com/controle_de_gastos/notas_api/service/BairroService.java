package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.BairroRepository;
import com.controle_de_gastos.notas_api.Repository.EstabelecimentoBairroJuncaoRepository;
import com.controle_de_gastos.notas_api.dto.BairroDTO;
import com.controle_de_gastos.notas_api.dto.BairroEstabDTO;
import com.controle_de_gastos.notas_api.dto.BairroEstabListDTO;
import com.controle_de_gastos.notas_api.dto.EstabelecimentoSimplesDTO;
import com.controle_de_gastos.notas_api.model.Bairro;
import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;
    private final EstabelecimentoBairroJuncaoRepository estabelecimentoBairroJuncaoRepository;
    private final EstabelecimentoService estabelecimentoService;

    public BairroDTO toDTO(Bairro bairro){
            return new BairroDTO(
                    bairro.getId(),
                    bairro.getNome()
            );
    }

    public BairroEstabDTO toBairroEstabDTO(EstabelecimentoBairroJuncao estabBairro){
        return new BairroEstabDTO(
                estabBairro.getBairro().getId(),
                estabBairro.getBairro().getNome(),
                estabelecimentoService.toEstabSimplesDTO(estabBairro.getEstabelecimento(), estabBairro.getEndereco())
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

    public List<BairroEstabDTO> listarEstabPorBairroId(Integer id){
        return estabelecimentoBairroJuncaoRepository.findByBairroId(id)
                .stream()
                .map(this::toBairroEstabDTO)
                .toList();
    }

    public List<BairroEstabListDTO> listarTodosEstabPorBairro(){
        return bairroRepository.findAll()
                .stream()
                .map(bairro -> new BairroEstabListDTO(
                        bairro.getId(),
                        bairro.getNome(),
                        bairro.getEstabelecimentoBairroJuncaos()
                                .stream()
                                .map(juncao -> new EstabelecimentoSimplesDTO(
                                        juncao.getEstabelecimento().getId(),
                                        juncao.getEstabelecimento().getNome(),
                                        juncao.getEndereco()
                                ))
                                .toList()

                ))
                .toList();

    }
    public BairroDTO salvar(BairroDTO bairroDTO){
        Bairro  bairro = new Bairro();
        bairro.setNome(bairroDTO.nome());
         return toDTO(bairroRepository.save(bairro));
    }

    public void  deletarPorId(Integer id){
        bairroRepository.deleteById(id);
    }

}

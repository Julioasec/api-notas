package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.AtributoRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.AtributoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.AtributoRespostaDTO;
import com.controle_de_gastos.notas_api.model.Atributo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtributoService {

    private final AtributoRepository atributoRepository;

    public AtributoRespostaDTO toRespostaDTO(Atributo atributo){
        return new AtributoRespostaDTO(
                atributo.getId(),
                atributo.getNome()
        );
    }

    public List<AtributoRespostaDTO> listarTodos() {
        return atributoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<AtributoRespostaDTO> buscarPorId(Integer id) {
        return atributoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public AtributoRespostaDTO criar(AtributoRequisicaoDTO atributoRequisicaoDTO){
        Atributo atributo = new Atributo();
        atributo.setNome(atributoRequisicaoDTO.nome());
        return toRespostaDTO(atributoRepository.save(atributo));
    }

    public boolean deletarPorId(Integer id){
        Optional<Atributo> atributo = atributoRepository.findById(id);

        if(!atributo.isPresent()){
            return false;
        }

        atributoRepository.deleteById(id);
        return true;
    }
}

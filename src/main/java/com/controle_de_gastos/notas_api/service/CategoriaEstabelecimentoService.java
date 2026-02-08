package com.controle_de_gastos.notas_api.service;
import com.controle_de_gastos.notas_api.dto.requisicao.CategoriaEstabelecimentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.CategoriaEstabelecimentoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.CategoriaEstabelecimentoRespostaDTO;
import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaEstabelecimentoService {

    private final CategoriaEstabelecimentoRepository categoriaEstabelecimentoRepository;

    public CategoriaEstabelecimentoRespostaDTO toRespostaDTO(CategoriaEstabelecimento categoriaEstabelecimento) {
        return new CategoriaEstabelecimentoRespostaDTO(
                categoriaEstabelecimento.getId(),
                categoriaEstabelecimento.getNome()
        );
    }

    public List<CategoriaEstabelecimentoRespostaDTO> listarTodos(){
        return categoriaEstabelecimentoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public Optional<CategoriaEstabelecimentoRespostaDTO> buscarPorId(Integer id){
        return categoriaEstabelecimentoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public CategoriaEstabelecimentoRespostaDTO criar(CategoriaEstabelecimentoRequisicaoDTO categoriaDTO) {
        CategoriaEstabelecimento categoria = CategoriaEstabelecimento.builder()
                .nome(categoriaDTO.nome())
                .build();
        return toRespostaDTO(categoriaEstabelecimentoRepository.save(categoria));
    }

    public Optional<CategoriaEstabelecimentoRespostaDTO> atualizarTudo(Integer id,  CategoriaEstabelecimentoRequisicaoDTO categoriaDTO) {
        CategoriaEstabelecimento catEstab = categoriaEstabelecimentoRepository.findById(id).orElse(null);

        if(catEstab ==null) return Optional.empty();

        catEstab.setNome(categoriaDTO.nome());
        return Optional.of(toRespostaDTO(categoriaEstabelecimentoRepository.save(catEstab)));

    }

    public boolean deletarPorId(Integer id){
        Optional<CategoriaEstabelecimento> catEstab = categoriaEstabelecimentoRepository.findById(id);

        if(catEstab.isEmpty()) return false;

        if(!catEstab.get().getEstabelecimentos().isEmpty()){
            throw new IllegalStateException("Não é possível deletar, existem dependências");
        }

        categoriaEstabelecimentoRepository.deleteById(id);
        return true;
    }


}

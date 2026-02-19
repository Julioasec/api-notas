package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.MetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoComNotaRespostaDTO;
import com.controle_de_gastos.notas_api.repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import com.controle_de_gastos.notas_api.repository.NotaMetodoPagametoJuncaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {

   private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final NotaMetodoPagametoJuncaoRepository notaMetodoPagametoJuncaoRepository;

    public MetodoPagamentoRespostaDTO toRespostaDTO(MetodoPagamento metodoPagamento) {
       return new MetodoPagamentoRespostaDTO(
               metodoPagamento.getId(),
               metodoPagamento.getNome());
   }

   public List<MetodoPagamentoRespostaDTO> listarTodos(){
      return metodoPagamentoRepository.findAll()
              .stream()
              .map(this::toRespostaDTO)
              .toList();
   }

   public Optional<MetodoPagamentoRespostaDTO> buscarPorId(Integer id){
       return metodoPagamentoRepository.findById(id)
               .map(this::toRespostaDTO);
   }

   public Optional<MetodoPagamentoComNotaRespostaDTO> listarNotasPorMetodoId(Integer id){
       Optional<MetodoPagamento> metodo = metodoPagamentoRepository.findById(id);
       if(metodo.isEmpty())return Optional.empty();

       List<NotaSimplesProjecaoDTO> notas = notaMetodoPagametoJuncaoRepository.findByMetodoPagamentoId(id)
               .stream()
               .map(juncao -> new NotaSimplesProjecaoDTO(
                       juncao.getNota().getId(),
                       juncao.getNota().getData(),
                       juncao.getNota().getTotal()
               ))
               .toList();

        return Optional.of(new MetodoPagamentoComNotaRespostaDTO(
                metodo.get().getId(),
                metodo.get().getNome(),
                notas
        ));
   }

   public List<MetodoPagamentoComNotaRespostaDTO> listarNotasPorMetodoPagamentoTodos(){
        return metodoPagamentoRepository.findAll()
                .stream()
                .map(metodo -> new MetodoPagamentoComNotaRespostaDTO(
                        metodo.getId(),
                        metodo.getNome(),
                        metodo.getNotaMetodoPagamentoJuncaos()
                                .stream()
                                .map(juncao -> new NotaSimplesProjecaoDTO(
                                        juncao.getNota().getId(),
                                        juncao.getNota().getData(),
                                        juncao.getNota().getTotal()
                                ))
                                .toList()
                ))
                .toList();

   }

   public MetodoPagamentoRespostaDTO criar(MetodoPagamentoRequisicaoDTO metodoDTO){
       MetodoPagamento metodoPagamento = MetodoPagamento.builder()
               .nome(metodoDTO.nome())
               .build();
       return toRespostaDTO(metodoPagamentoRepository.save(metodoPagamento));
   }

   public Optional<MetodoPagamentoRespostaDTO> atualizarTudo(Integer id, MetodoPagamentoRequisicaoDTO metodoDTO){
       return metodoPagamentoRepository.findById(id)
               .map(metodo -> {
                   metodo.setNome(metodoDTO.nome());
                   return toRespostaDTO(metodoPagamentoRepository.save(metodo));
               });
   }

   public boolean deletarPorId(Integer id){
       MetodoPagamento metodoPagamento = metodoPagamentoRepository.findById(id).orElse(null);
       if (metodoPagamento == null) return false;

       if(!metodoPagamento.getNotaMetodoPagamentoJuncaos().isEmpty()) throw new IllegalStateException("Não é possível deletar, existem dependências");

       metodoPagamentoRepository.deleteById(id);
       return true;
   }
}

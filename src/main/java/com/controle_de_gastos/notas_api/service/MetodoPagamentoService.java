package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.requisicao.MetodoPagamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {

   private final MetodoPagamentoRepository metodoPagamentoRepository;

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

   public MetodoPagamentoRespostaDTO criar(MetodoPagamentoRequisicaoDTO metodoDTO){
       MetodoPagamento metodoPagamento = new MetodoPagamento();
       metodoPagamento.setNome(metodoDTO.nome());
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

package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.dto.MetodoPagamentoDTO;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {

   private final MetodoPagamentoRepository metodoPagamentoRepository;

   public MetodoPagamentoDTO toDTO(MetodoPagamento metodoPagamento) {
       return new MetodoPagamentoDTO(
               metodoPagamento.getIdMetodo(),
               metodoPagamento.getNome());
   }

   public List<MetodoPagamentoDTO> listarTodos(){
      return metodoPagamentoRepository.findAll()
              .stream()
              .map(this::toDTO)
              .toList();
   }

   public Optional<MetodoPagamentoDTO> buscarPorId(Integer id){
       return metodoPagamentoRepository.findById(id)
               .map(this::toDTO);
   }

   public MetodoPagamentoDTO salvarMetodo(MetodoPagamento metodoPagamento){
       return toDTO(metodoPagamentoRepository.save(metodoPagamento));
   }

   public void deletarPorId(Integer id){
       metodoPagamentoRepository.deleteById(id);
   }
}

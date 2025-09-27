package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagamentoService {

   private MetodoPagamentoRepository metodoPagamentoRepository;

   public MetodoPagamentoService(MetodoPagamentoRepository metodoPagamentoRepository) {
       this.metodoPagamentoRepository = metodoPagamentoRepository;
   }


   public List<MetodoPagamento> listarTodos(){
      return metodoPagamentoRepository.findAll();
   }

   public Optional<MetodoPagamento> buscarPorId(Integer id){
       return metodoPagamentoRepository.findById(id);
   }

   public MetodoPagamento salvarMetodo(MetodoPagamento metodoPagamento){
       return metodoPagamentoRepository.save(metodoPagamento);
   }

   public void deletarPorId(Integer id){
       metodoPagamentoRepository.deleteById(id);
   }
}

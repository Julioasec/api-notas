package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.Repository.ParcelamentoRepository;
import com.controle_de_gastos.notas_api.dto.ParcelamentoDTO;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.model.Parcelamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelamentoService {
        private final ParcelamentoRepository parcelamentoRepository;
        private final NotaRepository notaRepository;

        private ParcelamentoDTO toDTO(Parcelamento parcelamento) {
            return new ParcelamentoDTO(
                    parcelamento.getIdParcelamento(),
                    parcelamento.getParcela(),
                    parcelamento.getDataPagamento(),
                    parcelamento.getValorParcela(),
                    parcelamento.getPago(),
                    parcelamento.getNota().getIdNota(),
                    parcelamento.getNota().getTotal()
            );
        }

        public List<ParcelamentoDTO> listarTodos(){
            return parcelamentoRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
    }

        public Optional<ParcelamentoDTO> buscarPorId(Integer idParcelamento){
            return parcelamentoRepository.findById(idParcelamento)
                    .map(this::toDTO);
    }


    public ParcelamentoDTO salvarParcelamento(Parcelamento parcelamento, Integer idNota){
            Nota nota = notaRepository.findById(idNota)
                    .orElseThrow(()->new RuntimeException("Nota não encontrada"));

            parcelamento.setNota(nota);
            return toDTO(parcelamentoRepository.save(parcelamento));
    }

    public void deletarPorId(Integer id){
             parcelamentoRepository.deleteById(id);
    }
}

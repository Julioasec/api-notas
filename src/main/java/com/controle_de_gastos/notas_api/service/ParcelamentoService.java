package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.dto.projecao.NotaSimplesProjecaoDTO;
import com.controle_de_gastos.notas_api.repository.NotaRepository;
import com.controle_de_gastos.notas_api.repository.ParcelamentoRepository;
import com.controle_de_gastos.notas_api.dto.resposta.ParcelamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ParcelamentoRequisicaoDTO;
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

        private ParcelamentoRespostaDTO toRespostaDTO(Parcelamento parcelamento) {
            return new ParcelamentoRespostaDTO(
                    parcelamento.getId(),
                    parcelamento.getParcela(),
                    parcelamento.getDataPagamento(),
                    parcelamento.getValorParcela(),
                    parcelamento.getPago(),
                    new NotaSimplesProjecaoDTO(
                            parcelamento.getNota().getId(),
                            parcelamento.getNota().getData(),
                            parcelamento.getNota().getTotal()
                    )
            );
        }

        public List<ParcelamentoRespostaDTO> listarParcelamentosDaNota(Integer notaId){
               return parcelamentoRepository.findByNotaId(notaId)
                       .stream()
                       .map(this::toRespostaDTO)
                       .toList();
        }

        public List<ParcelamentoRespostaDTO> listarTodos(){
            return parcelamentoRepository.findAll()
                    .stream()
                    .map(this::toRespostaDTO)
                    .toList();
    }

        public Optional<ParcelamentoRespostaDTO> buscarPorId(Integer id){
            return parcelamentoRepository.findById(id)
                    .map(this::toRespostaDTO);
    }


    public ParcelamentoRespostaDTO associar(ParcelamentoRequisicaoDTO parcelamentoRequisicaoDTO, Integer idNota){
            Nota nota = notaRepository.findById(idNota)
                    .orElseThrow(()->new RuntimeException("Nota não encontrada"));

            Parcelamento parcelamento = new Parcelamento();
            parcelamento.setNota(nota);
            parcelamento.setParcela(parcelamentoRequisicaoDTO.numeroParcela());
            parcelamento.setDataPagamento(parcelamentoRequisicaoDTO.dataPagamento());
            parcelamento.setValorParcela(parcelamentoRequisicaoDTO.valorParcela());
            parcelamento.setPago(parcelamentoRequisicaoDTO.pago());
            return toRespostaDTO(parcelamentoRepository.save(parcelamento));
    }

    public void deletarPorId(Integer id){
             parcelamentoRepository.deleteById(id);
    }
}

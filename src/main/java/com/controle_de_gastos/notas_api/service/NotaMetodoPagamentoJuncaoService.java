package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaMetodoPagametoJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.MetodoPagamentoDTO;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.dto.NotaMetodoPagamentoJuncaoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotasMetodoPagamentoRequisicao;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import com.controle_de_gastos.notas_api.model.Nota;
import com.controle_de_gastos.notas_api.model.NotaMetodoPagamentoJuncao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotaMetodoPagamentoJuncaoService {
    private final NotaMetodoPagametoJuncaoRepository notaMetodoPagametoJuncaoRepository;
    private final NotaRepository notaRepository;
    private final NotaService  notaService;
    private final MetodoPagamentoRepository metodoPagamentoRepository;
    private final MetodoPagamentoService metodoPagamentoService;



    public NotaMetodoPagamentoJuncaoDTO toDTO(NotaMetodoPagamentoJuncao  notaMetodoPagamentoJuncao) {
           NotaDTO notaDTO = notaService.toDTO(notaMetodoPagamentoJuncao.getNota());
           MetodoPagamentoDTO metodoPagamentoDTO = metodoPagamentoService.toDTO(notaMetodoPagamentoJuncao.getMetodoPagamento());
           Double valorPago = notaMetodoPagamentoJuncao.getValorPago();

            return new NotaMetodoPagamentoJuncaoDTO(
                    notaMetodoPagamentoJuncao.getIdNMPagamento(),
                    notaDTO,
                    metodoPagamentoDTO,
                    valorPago
            );
    }

    public List<NotaMetodoPagamentoJuncaoDTO> listarTodos(){
        return notaMetodoPagametoJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public NotaMetodoPagamentoJuncaoDTO asssociar(NotasMetodoPagamentoRequisicao notaMPReq) {
        Nota nota = notaRepository.findById(notaMPReq.getIdNota())
                .orElseThrow(()->new RuntimeException("Nota Não Encontrada"));

        MetodoPagamento metodo = metodoPagamentoRepository.findById(notaMPReq.getIdMetodo())
                .orElseThrow(()->new RuntimeException("Método de pagamento não encontrado"));

        NotaMetodoPagamentoJuncao notaMPJuncao = new NotaMetodoPagamentoJuncao();
        notaMPJuncao.setNota(nota);
        notaMPJuncao.setMetodoPagamento(metodo);

        notaMPJuncao.getNota().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);
        notaMPJuncao.getMetodoPagamento().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);
        notaMPJuncao.setValorPago(notaMPReq.getValorPago());


        return toDTO(notaMetodoPagametoJuncaoRepository.save(notaMPJuncao));
    }

    public Optional<NotaMetodoPagamentoJuncaoDTO> buscarPorId(Integer id){
        return notaMetodoPagametoJuncaoRepository.findById(id)
                .map(this::toDTO);
    }

    public void deletarPorId(Integer id){
        notaMetodoPagametoJuncaoRepository.deleteById(id);
    }
}

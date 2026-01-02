package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.repository.NotaMetodoPagametoJuncaoRepository;
import com.controle_de_gastos.notas_api.repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.resposta.MetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaRespostaDTO;
import com.controle_de_gastos.notas_api.dto.resposta.NotaMetodoPagamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotasMetodoPagamentoRequisicaoDTO;
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



    public NotaMetodoPagamentoRespostaDTO toRespostaDTO(NotaMetodoPagamentoJuncao  notaMetodoPagamentoJuncao) {
           NotaRespostaDTO notaRespostaDTO = notaService.toRespostaDTO(notaMetodoPagamentoJuncao.getNota());
           MetodoPagamentoRespostaDTO metodoPagamentoRespostaDTO = metodoPagamentoService.toRespostaDTO(notaMetodoPagamentoJuncao.getMetodoPagamento());
           Double valorPago = notaMetodoPagamentoJuncao.getValorPago();

            return new NotaMetodoPagamentoRespostaDTO(
                    notaMetodoPagamentoJuncao.getId(),
                    notaRespostaDTO,
                    metodoPagamentoRespostaDTO,
                    valorPago
            );
    }

    public List<NotaMetodoPagamentoRespostaDTO> listarTodos(){
        return notaMetodoPagametoJuncaoRepository.findAll()
                .stream()
                .map(this::toRespostaDTO)
                .toList();
    }

    public NotaMetodoPagamentoRespostaDTO asssociar(NotasMetodoPagamentoRequisicaoDTO notaMPResposta) {
        Nota nota = notaRepository.findById(notaMPResposta.idNota())
                .orElseThrow(()->new RuntimeException("Nota Não Encontrada"));

        MetodoPagamento metodo = metodoPagamentoRepository.findById(notaMPResposta.idMetodo())
                .orElseThrow(()->new RuntimeException("Método de pagamento não encontrado"));

        NotaMetodoPagamentoJuncao notaMPJuncao = new NotaMetodoPagamentoJuncao();
        notaMPJuncao.setNota(nota);
        notaMPJuncao.setMetodoPagamento(metodo);

        notaMPJuncao.getNota().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);
        notaMPJuncao.getMetodoPagamento().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);
        notaMPJuncao.setValorPago(notaMPResposta.valorPago());


        return toRespostaDTO(notaMetodoPagametoJuncaoRepository.save(notaMPJuncao));
    }

    public Optional<NotaMetodoPagamentoRespostaDTO> buscarPorId(Integer id){
        return notaMetodoPagametoJuncaoRepository.findById(id)
                .map(this::toRespostaDTO);
    }

    public void deletarPorId(Integer id){
        notaMetodoPagametoJuncaoRepository.deleteById(id);
    }
}

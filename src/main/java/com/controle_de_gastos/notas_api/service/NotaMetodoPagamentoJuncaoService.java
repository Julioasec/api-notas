package com.controle_de_gastos.notas_api.service;

import com.controle_de_gastos.notas_api.Repository.MetodoPagamentoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaMetodoPagametoJuncaoRepository;
import com.controle_de_gastos.notas_api.Repository.NotaRepository;
import com.controle_de_gastos.notas_api.dto.MetodoPagamentoDTO;
import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.dto.NotaMetodoPagamentoJuncaoDTO;
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

            return new NotaMetodoPagamentoJuncaoDTO(
                    notaMetodoPagamentoJuncao.getIdNMPagamento(),
                    notaDTO,
                    metodoPagamentoDTO
            );
    }

    public List<NotaMetodoPagamentoJuncaoDTO> listarTodos(){
        return notaMetodoPagametoJuncaoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public NotaMetodoPagamentoJuncaoDTO salvarJuncao(NotaMetodoPagamentoJuncao notaMPJuncao,
                                                     Integer idNota,
                                                     Integer idMetodo) {
        Nota nota = notaRepository.findById(idNota)
                .orElseThrow(()->new RuntimeException("Nota Não Encontrada"));

        MetodoPagamento metodo = metodoPagamentoRepository.findById(idMetodo)
                .orElseThrow(()->new RuntimeException("Método de pagamento não encontrado"));

        notaMPJuncao.setNota(nota);
        notaMPJuncao.setMetodoPagamento(metodo);

        notaMPJuncao.getNota().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);
        notaMPJuncao.getMetodoPagamento().getNotaMetodoPagamentoJuncaos().add(notaMPJuncao);

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

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.NotaRespostaDTO;
import com.controle_de_gastos.notas_api.dto._refazer_NotaPagamentoDTO;
import com.controle_de_gastos.notas_api.dto.resposta.ParcelamentoRespostaDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotaRequisicaoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.ParcelamentoRequisicaoDTO;
import com.controle_de_gastos.notas_api.service.NotaService;
import com.controle_de_gastos.notas_api.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;
    private final ParcelamentoService parcelamentoService;

    @GetMapping
    public List<NotaRespostaDTO> listarTodos(){
        return notaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotaRespostaDTO> buscarPorId(@PathVariable Integer id){
        return notaService.buscarPorid(id);
    }

    @PostMapping
    public NotaRespostaDTO criar(@RequestBody NotaRequisicaoDTO notaDTO){
        return notaService.criar(notaDTO);
    }

    @GetMapping("/{id}/pagamentos")
    public List<_refazer_NotaPagamentoDTO> listarPagamentos(@PathVariable Integer id){
        return notaService.listarPagamentosPorNota(id);
    }

    @GetMapping("/{id}/parcelamentos")
    public List<ParcelamentoRespostaDTO> listarParcelamentos(@PathVariable Integer id){
        return parcelamentoService.listarParcelamentosDaNota(id);
    }

    @PostMapping("/{notaId}/parcelamento")
    public ResponseEntity<ParcelamentoRespostaDTO> associarParcelamento(@RequestBody ParcelamentoRequisicaoDTO parcelamentoDTO, @PathVariable Integer notaId){
        return ResponseEntity.status(201).body(parcelamentoService.associar(parcelamentoDTO, notaId));
    }

    @DeleteMapping("/{notaId}/parcelamento/{parcelamentoId}")
    public ResponseEntity<Void> deletarParcela(@PathVariable Integer notaId,@PathVariable Integer parcelamentoId ){
        boolean isDeletado;
        try{
           isDeletado = parcelamentoService.deletarPorId(notaId, parcelamentoId);

        }catch (Exception ex){
            return ResponseEntity.status(403).build();
        }

        if (isDeletado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        notaService.deletarPorId(id);
    }
}

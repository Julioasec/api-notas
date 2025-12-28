package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.NotaDTO;
import com.controle_de_gastos.notas_api.dto.ParcelamentoDTO;
import com.controle_de_gastos.notas_api.dto.requisicao.NotaRequisicao;
import com.controle_de_gastos.notas_api.dto.requisicao.ParcelamentoRequisicao;
import com.controle_de_gastos.notas_api.service.NotaService;
import com.controle_de_gastos.notas_api.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota")
@RequiredArgsConstructor
public class NotaController {
   @Autowired
    private NotaService notaService;
    @Autowired
    private ParcelamentoService parcelamentoService;
    @GetMapping
    public List<NotaDTO> listarTodos(){
        return notaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<NotaDTO> buscarPorId(@PathVariable Integer id){
        return notaService.buscarPorid(id);
    }

    @PostMapping
    public NotaDTO salvarNota(@RequestBody NotaRequisicao notaRequisicao){
        return notaService.salvarNota(notaRequisicao);
    }

    @GetMapping("/{notaId}/parcelamentos")
    public List<ParcelamentoDTO> listarParcelamentos(@PathVariable Integer notaId){
        return parcelamentoService.listarParcelamentosDaNota(notaId);
    }

    @PostMapping("/{notaId}/parcelamento")
    public ParcelamentoDTO salvarParcelamento(@RequestBody ParcelamentoRequisicao parcelamentoRequisicao, @PathVariable Integer notaId){
        return parcelamentoService.salvarParcelamento(parcelamentoRequisicao, notaId);
    }

    @DeleteMapping
    public void deletarPorId(Integer id){
        notaService.deletarPorId(id);
    }
}

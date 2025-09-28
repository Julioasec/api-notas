package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.Repository.ParcelamentoRepository;
import com.controle_de_gastos.notas_api.dto.ParcelamentoDTO;
import com.controle_de_gastos.notas_api.model.Parcelamento;
import com.controle_de_gastos.notas_api.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcelamento")
@RequiredArgsConstructor
public class ParcelamentoController {
        private final ParcelamentoRepository parcelamentoRepository;
        private final ParcelamentoService parcelamentoService;

        @GetMapping
        public List<ParcelamentoDTO> listarTodos(){
            return parcelamentoService.listarTodos();
        }

        @GetMapping("/{id}")
        public Optional<ParcelamentoDTO> listarPorId(@PathVariable Integer id){
            return parcelamentoService.buscarPorId(id);
        }

        @PostMapping
        public ParcelamentoDTO salvarParcelamento(@RequestBody Parcelamento parcelamento,@RequestParam(name = "idNota") Integer idNota){
            return parcelamentoService.salvarParcelamento(parcelamento, idNota);
        }

        @DeleteMapping
        public void deletarPorId(Integer id){
            parcelamentoService.deletarPorId(id);
        }
}

package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.ParcelamentoDTO;
import com.controle_de_gastos.notas_api.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcelamentos/")
@RequiredArgsConstructor
public class ParcelamentoController {
        private final ParcelamentoService parcelamentoService;

        @GetMapping
        public List<ParcelamentoDTO> listarTodos(){
            return parcelamentoService.listarTodos();
        }

        @GetMapping("/{parcelamentoId}")
        public Optional<ParcelamentoDTO> listarPorId(@PathVariable Integer parcelamentoId){
            return parcelamentoService.buscarPorId(parcelamentoId);
        }

        //post está no NotaController

        @DeleteMapping
        public void deletarPorId(Integer id){
            parcelamentoService.deletarPorId(id);
        }
}

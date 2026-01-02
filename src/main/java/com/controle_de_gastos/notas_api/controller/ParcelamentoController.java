package com.controle_de_gastos.notas_api.controller;

import com.controle_de_gastos.notas_api.dto.resposta.ParcelamentoRespostaDTO;
import com.controle_de_gastos.notas_api.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcelamentos")
@RequiredArgsConstructor
public class ParcelamentoController {

        private final ParcelamentoService parcelamentoService;

        @GetMapping
        public List<ParcelamentoRespostaDTO> listarTodos(){
            return parcelamentoService.listarTodos();
        }

        @GetMapping("/{id}")
        public Optional<ParcelamentoRespostaDTO> listarPorId(@PathVariable Integer id){
            return parcelamentoService.buscarPorId(id);
        }

        //post está no NotaController

        @DeleteMapping
        public void deletarPorId(Integer id){
            parcelamentoService.deletarPorId(id);
        }
}

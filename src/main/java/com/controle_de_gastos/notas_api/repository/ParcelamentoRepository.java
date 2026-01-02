package com.controle_de_gastos.notas_api.repository;

import com.controle_de_gastos.notas_api.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Integer> {
    List<Parcelamento> findByNotaId(Integer notaId);
}

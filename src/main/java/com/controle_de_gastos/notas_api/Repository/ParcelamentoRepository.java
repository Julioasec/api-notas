package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.Parcelamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelamentoRepository extends JpaRepository<Parcelamento, Integer> {
}

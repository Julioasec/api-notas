package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.ItemTipo;
import com.controle_de_gastos.notas_api.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento,Integer> {
}

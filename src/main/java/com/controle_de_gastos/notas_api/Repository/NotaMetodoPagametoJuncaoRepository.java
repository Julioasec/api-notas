package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.NotaMetodoPagamentoJuncao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaMetodoPagametoJuncaoRepository extends JpaRepository<NotaMetodoPagamentoJuncao,Integer> {
    List<NotaMetodoPagamentoJuncao> findByNotaId(Integer notaId);
}

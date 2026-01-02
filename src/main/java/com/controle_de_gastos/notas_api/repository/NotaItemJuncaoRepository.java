package com.controle_de_gastos.notas_api.repository;

import com.controle_de_gastos.notas_api.model.NotaItemJuncao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaItemJuncaoRepository extends JpaRepository<NotaItemJuncao, Integer> {
}

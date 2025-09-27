package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
}

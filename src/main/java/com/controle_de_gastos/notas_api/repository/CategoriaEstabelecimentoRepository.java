package com.controle_de_gastos.notas_api.repository;

import com.controle_de_gastos.notas_api.model.CategoriaEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEstabelecimentoRepository extends JpaRepository<CategoriaEstabelecimento, Integer> {
}

package com.controle_de_gastos.notas_api.repository;

import com.controle_de_gastos.notas_api.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro,Integer> {
}

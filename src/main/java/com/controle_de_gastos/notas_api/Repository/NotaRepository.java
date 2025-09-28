package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}

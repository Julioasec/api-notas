package com.controle_de_gastos.notas_api.Repository;


import com.controle_de_gastos.notas_api.model.NotasCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotasCategoriaRepository extends JpaRepository<NotasCategoria, Integer> {
}

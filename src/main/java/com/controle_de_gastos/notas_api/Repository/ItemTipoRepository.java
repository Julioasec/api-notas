package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.ItemTipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTipoRepository extends JpaRepository<ItemTipo,Integer> {
}

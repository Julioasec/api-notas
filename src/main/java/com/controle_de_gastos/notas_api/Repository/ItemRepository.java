package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}

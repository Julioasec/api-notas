package com.controle_de_gastos.notas_api.Repository;

import com.controle_de_gastos.notas_api.model.ItemAtributoJuncao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemAtributoJuncaoRepository extends JpaRepository<ItemAtributoJuncao, Integer> {
    List<ItemAtributoJuncao> findByItemId(Integer idItem);
}

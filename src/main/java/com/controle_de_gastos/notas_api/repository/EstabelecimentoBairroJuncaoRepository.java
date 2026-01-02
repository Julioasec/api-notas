package com.controle_de_gastos.notas_api.repository;

import com.controle_de_gastos.notas_api.model.EstabelecimentoBairroJuncao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstabelecimentoBairroJuncaoRepository extends JpaRepository<EstabelecimentoBairroJuncao,Integer> {
    List<EstabelecimentoBairroJuncao> findByBairroId(Integer id);
    List<EstabelecimentoBairroJuncao> findByEstabelecimentoId(Integer id);
}

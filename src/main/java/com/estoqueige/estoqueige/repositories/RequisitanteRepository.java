package com.estoqueige.estoqueige.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.models.Requisitante;

@Repository
public interface RequisitanteRepository extends JpaRepository<Requisitante, Long>{
    @Query(value = "SELECT * FROM requisitante WHERE is_ativo = 1 and req_id > 1", nativeQuery = true)
    List<Requisitante> buscarRequisitantesAtivos();
}

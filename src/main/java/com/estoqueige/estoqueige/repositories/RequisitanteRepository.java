package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Requisitante;

@Repository
public interface RequisitanteRepository extends JpaRepository<Requisitante, Long>{
    
}

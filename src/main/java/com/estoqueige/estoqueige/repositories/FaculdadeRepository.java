package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Faculdade;

@Repository
public interface FaculdadeRepository extends JpaRepository<Faculdade, Long> {
    
}

package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Faculdade;
import com.estoqueige.estoqueige.models.UnidadeProduto;

@Repository
public interface UnidadeProdutoRepository extends JpaRepository<UnidadeProduto, Long> {
    
}

package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.CategoriaProduto;


@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
    
}

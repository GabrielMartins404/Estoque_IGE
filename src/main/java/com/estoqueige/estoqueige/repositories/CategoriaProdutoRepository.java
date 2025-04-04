package com.estoqueige.estoqueige.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.CategoriaProduto;


@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
    @Query(value = "SELECT * FROM categoria_produto WHERE is_ativo = 1", nativeQuery = true)
    List<CategoriaProduto> buscarCategoriasAtivas();
}

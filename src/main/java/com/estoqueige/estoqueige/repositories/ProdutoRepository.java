package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}

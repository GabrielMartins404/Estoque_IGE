package com.estoqueige.estoqueige.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Movimentacao;
import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.models.enums.MovStatus;
import com.estoqueige.estoqueige.models.enums.MovTipo;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Query(value = "SELECT * FROM movimentacao WHERE mov_tipo = :tipo AND mov_status = :status", nativeQuery = true)
    List<Movimentacao> buscarMovimentacaosPorTipo(@Param("tipo")String tipo, @Param("status")String status);
    

}

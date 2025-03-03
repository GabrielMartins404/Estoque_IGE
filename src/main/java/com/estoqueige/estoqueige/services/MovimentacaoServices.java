package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Movimentacao;
import com.estoqueige.estoqueige.models.ProdutoMovimentacao;
import com.estoqueige.estoqueige.repositories.MovimentacaoRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoServices {
    private final MovimentacaoRepository movimentacaoRepository;

    private final MovimentacaoEstoqueServices movimentacaoEstoqueServices;

    public MovimentacaoServices(MovimentacaoRepository movimentacaoRepository, MovimentacaoEstoqueServices movimentacaoEstoqueServices) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoEstoqueServices = movimentacaoEstoqueServices;
    }

    /* Método services */

    public Movimentacao buscarMovimentacaoPorId(Long id){
        Movimentacao movimentacao = this.movimentacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Falha ao buscar Movimentacao"));

        return movimentacao;
    }

    public List<Movimentacao> buscarMovimentacoes(){
        List<Movimentacao> movimentacoes = this.movimentacaoRepository.findAll();
        return movimentacoes;
    }

    //Método para salvar a movimentação no Banco de Dados
    @Transactional
    public void salvarMovimentacao(Movimentacao movimentacao) {
        //Adicionar validação

        //Salvar a movimentação no banco de dados
        movimentacao.setMovId(null);
        movimentacao.setMovStatus("F");
        movimentacaoRepository.save(movimentacao);
        for (ProdutoMovimentacao produtoMovimentacao: movimentacao.getProdutosMov()){
            movimentacaoEstoqueServices.salvarMovimentacaoEstoque(movimentacao.getReferencia(), produtoMovimentacao);
        }
    }

    @Transactional
    public Movimentacao atualizarMovimentacao(Movimentacao movimentacao){
        Movimentacao newMovimentacao = this.buscarMovimentacaoPorId(movimentacao.getMovId());
        BeanUtils.copyProperties(movimentacao, newMovimentacao, "getMovId");

        return this.movimentacaoRepository.save(newMovimentacao);
    }

    public void cancelarMovimentacao(Movimentacao movimentacao){

        movimentacao.setMovStatus("C");
        this.atualizarMovimentacao(movimentacao);
        
        for (ProdutoMovimentacao produtoMovimentacao : movimentacao.getProdutosMov()) {
            this.movimentacaoEstoqueServices.salvarMovimentacaoEstoque(movimentacao.getReferencia(), produtoMovimentacao);
        }
    }
}

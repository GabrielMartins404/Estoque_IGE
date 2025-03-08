package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.dto.MovEstoqueDto;
import com.estoqueige.estoqueige.models.Movimentacao;
import com.estoqueige.estoqueige.models.MovimentacaoEstoque;
import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.models.ProdutoMovimentacao;
import com.estoqueige.estoqueige.repositories.MovimentacaoEstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MovimentacaoEstoqueServices {
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    private final ProdutoServices produtoServices;

    public MovimentacaoEstoqueServices(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, ProdutoServices produtoServices) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.produtoServices = produtoServices;
    }

    /* Métodos services */

    public Float calcularQtdPosterior(Movimentacao movimentacao, Float qtdAtual, Float qtdMovimentada){
        Float qtdPosterior = 0f;

        if(movimentacao.getMovTipo().equals("E") && movimentacao.getMovStatus().equals("F")){
            qtdPosterior = qtdAtual + qtdMovimentada;
        }else if(movimentacao.getMovTipo().equals("S") && movimentacao.getMovStatus().equals("F")){
            qtdPosterior = qtdAtual - qtdMovimentada;
        }else if(movimentacao.getMovStatus().equals("C")){
            if(movimentacao.getMovTipo().equals("E")){
                qtdPosterior = qtdAtual - qtdMovimentada;
            }else{
                qtdPosterior = qtdAtual + qtdMovimentada;
            }
        }
        return qtdPosterior;
    }

    @Transactional
    public void salvarMovimentacaoEstoque(Movimentacao movimentacao, ProdutoMovimentacao produtoMovimentacao) {
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();

        movimentacaoEstoque.setMovEstData(LocalDate.now());
        movimentacaoEstoque.setMovEstQtd(produtoMovimentacao.getProMovQtdProduto());
        movimentacaoEstoque.setMovEstTipo(movimentacao.getMovTipo());
        movimentacaoEstoque.setMovEstMovimentacao(movimentacao);
        movimentacaoEstoque.setMovEstProduto(produtoMovimentacao.getProMovProduto());

        Produto produto = this.produtoServices.buscarProdutoPorId(produtoMovimentacao.getProMovProduto().getProId());

        movimentacaoEstoque.setMovEstQtdAnterior(produto.getProQtd());
        Float qtdPosterior = calcularQtdPosterior(movimentacao, produto.getProQtd(), produtoMovimentacao.getProMovQtdProduto());

        produto.setProQtd(qtdPosterior);
        movimentacaoEstoque.setMovEstQtdPosterior(qtdPosterior);
        //Status F: Finalizado, Status C: Cancelado
        //MovTipo E: Entrada, MovTipo S: Saída
        
        if(movimentacao.getMovStatus().equals("F") || movimentacao.getMovStatus().equals("C")){
            this.produtoServices.atualizarProduto(produto);
        }else{
            throw new RuntimeException("Tipo de movimentação inválido. Deve ser 'E' (entrada) ou 'S' (saída).");
        }
        this.movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

}

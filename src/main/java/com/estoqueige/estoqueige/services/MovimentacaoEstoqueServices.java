package com.estoqueige.estoqueige.services;

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
    @Transactional
    public void salvarMovimentacaoEstoque(Movimentacao movimentacao, ProdutoMovimentacao produtoMovimentacao) {
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();

        movimentacaoEstoque.setMovEstData(LocalDate.now());
        movimentacaoEstoque.setMovEstQtd(produtoMovimentacao.getProMovQtdProduto());
        movimentacaoEstoque.setMovEstTipo(movimentacao.getMovTipo());
        movimentacaoEstoque.setMovEstMovimentacao(movimentacao);
        movimentacaoEstoque.setMovEstProduto(produtoMovimentacao.getProMovProduto());
        movimentacaoEstoque.setMovEstQtdAnterior(produtoMovimentacao.getProMovProduto().getProQtd());
        movimentacaoEstoque.setMovEstQtdPosterior(produtoMovimentacao.getProMovProduto().getProQtd() + produtoMovimentacao.getProMovQtdProduto());

        //Status F: Finalizado, Status C: Cancelado
        //MovTipo E: Entrada, MovTipo S: Saída
        if(movimentacao.getMovTipo().equals("E") && movimentacao.getMovStatus().equals("F")){
            this.gerarEntradaDeProdutos(produtoMovimentacao);
        }else if(movimentacao.getMovTipo().equals("S") && movimentacao.getMovStatus().equals("F")){
            this.gerarSaidaDeProdutos(produtoMovimentacao);
        }else if(movimentacao.getMovStatus().equals("C")){
            this.cancelarOperacaoProduto(produtoMovimentacao, movimentacao.getMovTipo());
        }else{
            throw new RuntimeException("Tipo de movimentação inválido. Deve ser 'E' (entrada) ou 'S' (saída).");
        }
        this.movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

    @Transactional
    public void gerarEntradaDeProdutos(ProdutoMovimentacao produtoMovimentacao) {
        Produto produto = produtoMovimentacao.getProMovProduto();
        produto.setProQtd(produto.getProQtd() + produtoMovimentacao.getProMovQtdProduto());

        try {
            produtoServices.atualizarProduto(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar entrada de produtos: " + e.getMessage());
        }
    }

    @Transactional
    public void gerarSaidaDeProdutos(ProdutoMovimentacao produtoMovimentacao) {
        Produto produto = produtoMovimentacao.getProMovProduto();
        produto.setProQtd(produto.getProQtd() - produtoMovimentacao.getProMovQtdProduto());

        try {
            produtoServices.atualizarProduto(produto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar entrada de produtos: " + e.getMessage());
        }
    }

    public void cancelarOperacaoProduto(ProdutoMovimentacao produtoMovimentacao, String operacaoOriginal){

        if(operacaoOriginal.equals("E")){
            gerarSaidaDeProdutos(produtoMovimentacao);
        }else if(operacaoOriginal.equals("S")){
            gerarEntradaDeProdutos(produtoMovimentacao);
        }else{
            throw new RuntimeException("Ocorreu um erro ao executar o cancelamento");
        }
    }

}

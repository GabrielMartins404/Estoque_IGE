package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.dto.MovEstoqueDto;
import com.estoqueige.estoqueige.dto.ProdutoDto;
import com.estoqueige.estoqueige.dto.ProdutoMovimentacaoDto;
import com.estoqueige.estoqueige.models.MovimentacaoEstoque;
import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServices {
    private final ProdutoRepository produtoRepository;
    
    public ProdutoServices(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    /* Método dos services */

    public MovEstoqueDto gerarMovEstoqueDto(MovimentacaoEstoque movimentacaoEstoque){
        MovEstoqueDto movEstoqueDto = new MovEstoqueDto();
        
        movEstoqueDto.setMovEstId(movimentacaoEstoque.getMovEstId());
        movEstoqueDto.setMovEstData(movimentacaoEstoque.getMovEstData());
        movEstoqueDto.setMovEstQtd(movimentacaoEstoque.getMovEstQtd());
        movEstoqueDto.setMovEstQtdAnterior(movimentacaoEstoque.getMovEstQtdAnterior());
        movEstoqueDto.setMovEstQtdPosterior(movimentacaoEstoque.getMovEstQtdPosterior());
        movEstoqueDto.setMovEstTipo(movimentacaoEstoque.getMovEstTipo());
        movEstoqueDto.setMovEstMovimentacao(movimentacaoEstoque.getMovEstMovimentacao());

        return movEstoqueDto;
    }

    public ProdutoDto gerarProdutoDto(Produto produto){
        //Preciso criar a movimentacaoSemProdutoDto e inserir em EstoqueMovimentacao, e após isso, inserir no Produto
        List<MovEstoqueDto> movEstoqueDtos = new ArrayList<>();
        for (MovimentacaoEstoque movimentacaoEstoque : produto.getMovimentacaoEstoque()) {
            movEstoqueDtos.add(this.gerarMovEstoqueDto(movimentacaoEstoque));
        }

        ProdutoDto produtoDto = new ProdutoDto(
            produto.getProId(),
            produto.getProNome(),
            produto.getProSipac(),
            produto.getProQtd(),
            produto.getIsAtivo()
        );

        return produtoDto;
    }

    public Produto buscarProdutoPorId(Long id) {
        Produto produto = this.produtoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Produto no encontrado"));
        return produto;
    }

    public List<ProdutoDto> buscarTodosProdutos() {
        List<Produto> produtos = this.produtoRepository.findAll();
        
        List<ProdutoDto> produtoDtos = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDtos.add(gerarProdutoDto(produto));
        }   
        return produtoDtos;
    }

    public Produto cadastrarProduto(Produto produto) {
        produto.setProId(null);
        return this.produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Produto produto) {
        Produto newProduto = this.buscarProdutoPorId(produto.getProId());

        BeanUtils.copyProperties(produto, newProduto, "proId");
        return this.produtoRepository.save(newProduto);
    }

    public Produto alterarStatusAtivoProduto(Long id) {
        Produto produto = this.buscarProdutoPorId(id);

        produto.setIsAtivo(!produto.getIsAtivo());
        return this.atualizarProduto(produto);
    }
}

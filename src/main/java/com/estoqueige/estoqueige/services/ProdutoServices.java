package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.dto.ProdutoMovimentacaoDto;
import com.estoqueige.estoqueige.models.Produto;
import com.estoqueige.estoqueige.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServices {
    private final ProdutoRepository produtoRepository;

    public ProdutoServices(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    /* Método dos services */
    public Produto buscarProdutoPorId(Long id) {
        Produto produto = this.produtoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Produto no encontrado"));
        return produto;
    }

    public List<Produto> buscarTodosProdutos() {
        List<Produto> produtos = this.produtoRepository.findAll();
        return produtos;
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

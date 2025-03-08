package com.estoqueige.estoqueige.dto;

import com.estoqueige.estoqueige.models.Produto;
import java.util.Objects;

public class ProdutoMovimentacaoDto {
    private ProdutoDto produto;

    private Float qtdProduto;


    public ProdutoMovimentacaoDto() {
    }

    public ProdutoMovimentacaoDto(ProdutoDto produto, Float qtdProduto) {
        this.produto = produto;
        this.qtdProduto = qtdProduto;
    }

    public ProdutoDto getProduto() {
        return this.produto;
    }

    public void setProduto(ProdutoDto produto) {
        this.produto = produto;
    }

    public Float getQtdProduto() {
        return this.qtdProduto;
    }

    public void setQtdProduto(Float qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    @Override
    public String toString() {
        return "{" +
            " produto='" + getProduto() + "'" +
            ", qtdProduto='" + getQtdProduto() + "'" +
            "}";
    }
    
}

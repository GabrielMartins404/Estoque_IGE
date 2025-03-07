package com.estoqueige.estoqueige.dto;

import com.estoqueige.estoqueige.models.Produto;
import java.util.Objects;

public class ProdutoMovimentacaoDto {
    private Produto produto;

    private Float qtdProduto;


    public ProdutoMovimentacaoDto() {
    }

    public ProdutoMovimentacaoDto(Produto produto, Float qtdProduto) {
        this.produto = produto;
        this.qtdProduto = qtdProduto;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
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

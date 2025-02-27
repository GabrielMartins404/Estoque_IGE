package com.estoqueige.estoqueige.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = ProdutoMovimentacao.TABLE_NAME)
public class ProdutoMovimentacao {
    public static final String TABLE_NAME = "produtoMovimentacao";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proMovId", unique = true)
    private Long proMovId;

    @Column(name = "proMovQtdProduto", nullable = false)
    private Float proMovQtdProduto;

    /* Definição das chaves estrangeiras */
    @ManyToOne
    @JoinColumn(name = "proMovProduto", nullable = false)
    private Produto proMovProduto;

    @ManyToOne
    @JoinColumn(name = "proMovMovimentacao", nullable = true)
    private Movimentacao proMovMovimentacao;

    @ManyToOne
    @JoinColumn(name = "proMovRequisicao", nullable = true)
    private Requisicao proMovRequisicao;

    public ProdutoMovimentacao() {
    }
    
    public ProdutoMovimentacao(Long proMovId, Float proMovQtdProduto, Produto proMovProduto, Movimentacao proMovMovimentacao) {
        this.proMovId = proMovId;
        this.proMovQtdProduto = proMovQtdProduto;
        this.proMovProduto = proMovProduto;
        this.proMovMovimentacao = proMovMovimentacao;
    }

    public ProdutoMovimentacao(Long proMovId, Float proMovQtdProduto, Produto proMovProduto, Requisicao proMovRequisicao) {
        this.proMovId = proMovId;
        this.proMovQtdProduto = proMovQtdProduto;
        this.proMovProduto = proMovProduto;
        this.proMovRequisicao = proMovRequisicao;
    }

    public Long getProMovId() {
        return this.proMovId;
    }

    public void setProMovId(Long proMovId) {
        this.proMovId = proMovId;
    }

    public Float getProMovQtdProduto() {
        return this.proMovQtdProduto;
    }

    public void setProMovQtdProduto(Float proMovQtdProduto) {
        this.proMovQtdProduto = proMovQtdProduto;
    }

    public Produto getProMovProduto() {
        return this.proMovProduto;
    }

    public void setProMovProduto(Produto proMovProduto) {
        this.proMovProduto = proMovProduto;
    }

    public Movimentacao getProMovMovimentacao() {
        return this.proMovMovimentacao;
    }

    public void setProMovMovimentacao(Movimentacao proMovMovimentacao) {
        this.proMovMovimentacao = proMovMovimentacao;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proMovId, proMovQtdProduto, proMovProduto);
    }

}

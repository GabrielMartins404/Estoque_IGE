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
@Table(name = ProdutoRequisicao.TABLE_NAME)
public class ProdutoRequisicao {
    public static final String TABLE_NAME = "produtoRequisicao";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proReqId", unique = true)
    private Long proReqId;

    @Column(name = "proReqQtdProduto", nullable = false)
    private Float proReqQtdProduto;

     /* Definição das chaves estrangeiras */
    @ManyToOne
    @JoinColumn(name = "proReqProduto", nullable = false)
    private Produto proReqProduto;

    @ManyToOne
    @JoinColumn(name = "proReqRequisicao", nullable = false)
    private Requisicao proReqRequisicao;


    public ProdutoRequisicao() {
    }

    public ProdutoRequisicao(Long proReqId, Float proReqQtdProduto, Produto proReqProduto, Requisicao proReqRequisicao) {
        this.proReqId = proReqId;
        this.proReqQtdProduto = proReqQtdProduto;
        this.proReqProduto = proReqProduto;
        this.proReqRequisicao = proReqRequisicao;
    }

    public Long getProReqId() {
        return this.proReqId;
    }

    public void setProReqId(Long proReqId) {
        this.proReqId = proReqId;
    }

    public Float getProReqQtdProduto() {
        return this.proReqQtdProduto;
    }

    public void setProReqQtdProduto(Float proReqQtdProduto) {
        this.proReqQtdProduto = proReqQtdProduto;
    }

    public Produto getProReqProduto() {
        return this.proReqProduto;
    }

    public void setProReqProduto(Produto proReqProduto) {
        this.proReqProduto = proReqProduto;
    }

    public Requisicao getProReqRequisicao() {
        return this.proReqRequisicao;
    }

    public void setProReqRequisicao(Requisicao proReqRequisicao) {
        this.proReqRequisicao = proReqRequisicao;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proReqId, proReqQtdProduto, proReqProduto, proReqRequisicao);
    }

}

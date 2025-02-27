package com.estoqueige.estoqueige.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = ProdutoEstoque.TABLE_NAME)
public class ProdutoEstoque {
    public static final String TABLE_NAME = "produtoEstoque";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proEsId", unique = true)
    private Long proEsId;

    @Column(name = "proEsData", nullable = false)
    @NotNull
    private LocalDate proEsData;

    @Column(name = "proEsTipo", length = 2,nullable = false)
    @NotBlank
    private String proEsTipo;

    @Column(name = "proEsQtd", nullable = false)
    @NotNull
    private Float proEsQtd;

    /* */

    @ManyToOne
    @JoinColumn(name = "proEsMovimentacao", nullable = false)
    private Movimentacao proEsMovimentacao;

    @ManyToOne
    @JoinColumn(name = "proEsProduto", nullable = false)
    private Produto proEsProduto;

    public ProdutoEstoque() {
    }

    public ProdutoEstoque(Long proEsId, LocalDate proEsData, String proEsTipo, Float proEsQtd, Movimentacao proEsMovimentacao, Produto proEsProduto) {
        this.proEsId = proEsId;
        this.proEsData = proEsData;
        this.proEsTipo = proEsTipo;
        this.proEsQtd = proEsQtd;
        this.proEsMovimentacao = proEsMovimentacao.getReferencia();
        this.proEsProduto = proEsProduto;
    }

    public Long getProEsId() {
        return this.proEsId;
    }

    public void setProEsId(Long proEsId) {
        this.proEsId = proEsId;
    }

    public LocalDate getProEsData() {
        return this.proEsData;
    }

    public void setProEsData(LocalDate proEsData) {
        this.proEsData = proEsData;
    }

    public String getProEsTipo() {
        return this.proEsTipo;
    }

    public void setProEsTipo(String proEsTipo) {
        this.proEsTipo = proEsTipo;
    }

    public Float getProEsQtd() {
        return this.proEsQtd;
    }

    public void setProEsQtd(Float proEsQtd) {
        this.proEsQtd = proEsQtd;
    }

    public Movimentacao getProEsMovimentacao() {
        return this.proEsMovimentacao;
    }

    public void setProEsMovimentacao(Movimentacao proEsMovimentacao) {
        this.proEsMovimentacao = proEsMovimentacao;
    }

    public Produto getProEsProduto() {
        return this.proEsProduto;
    }

    public void setProEsProduto(Produto proEsProduto) {
        this.proEsProduto = proEsProduto;
    }

    public ProdutoEstoque proEsId(Long proEsId) {
        setProEsId(proEsId);
        return this;
    }

    public ProdutoEstoque proEsData(LocalDate proEsData) {
        setProEsData(proEsData);
        return this;
    }

    public ProdutoEstoque proEsTipo(String proEsTipo) {
        setProEsTipo(proEsTipo);
        return this;
    }

    public ProdutoEstoque proEsQtd(Float proEsQtd) {
        setProEsQtd(proEsQtd);
        return this;
    }

    public ProdutoEstoque proEsMovimentacao(Movimentacao proEsMovimentacao) {
        setProEsMovimentacao(proEsMovimentacao);
        return this;
    }

    public ProdutoEstoque proEsProduto(Produto proEsProduto) {
        setProEsProduto(proEsProduto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proEsId, proEsData, proEsTipo, proEsQtd, proEsMovimentacao, proEsProduto);
    }
}

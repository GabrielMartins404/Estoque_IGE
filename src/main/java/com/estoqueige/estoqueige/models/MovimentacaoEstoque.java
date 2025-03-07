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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = MovimentacaoEstoque.TABLE_NAME)
public class MovimentacaoEstoque {
    public static final String TABLE_NAME = "movimentacaoEstoque";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movEstId", unique = true)
    private Long movEstId;

    @Column(name = "movEstData", nullable = false)
    @NotNull
    private LocalDate movEstData;

    @Column(name = "movEstTipo", length = 2,nullable = false)
    @NotBlank
    private String movEstTipo;

    @Column(name = "movEstQtd", nullable = false)
    @NotNull
    private Float movEstQtd;

    @Column(name = "movEstQtdAnterior", nullable = false)
    @NotNull
    private Float movEstQtdAnterior;

    @Column(name = "movEstQtdPosterior", nullable = false)
    @NotNull
    private Float movEstQtdPosterior;

    /* Chaves estrangeiras */

    @ManyToOne
    @JoinColumn(name = "movEstProduto", nullable = false)
    @JsonBackReference // Definição do lado "filho"
    private Produto movEstProduto;
    
    @ManyToOne
    @JoinColumn(name = "movEstMovimentacao", nullable = false)
    //@JsonBackReference // Definição do lado "filho"
    private Movimentacao movEstMovimentacao;

    public MovimentacaoEstoque() {
    }

    public MovimentacaoEstoque(Produto movEstProduto, Float movEstQtdPosterior, Float movEstQtdAnterior, Float movEstQtd, String movEstTipo, LocalDate movEstData, Long movEstId) {
        this.movEstProduto = movEstProduto;
        //this.movEstMovimentacao = movEstMovimentacao;
        this.movEstQtdPosterior = movEstQtdPosterior;
        this.movEstQtdAnterior = movEstQtdAnterior;
        this.movEstQtd = movEstQtd;
        this.movEstTipo = movEstTipo;
        this.movEstData = movEstData;
        this.movEstId = movEstId;
    }

    public Long getMovEstId() {
        return this.movEstId;
    }

    public void setMovEstId(Long movEstId) {
        this.movEstId = movEstId;
    }

    public LocalDate getMovEstData() {
        return this.movEstData;
    }

    public void setMovEstData(LocalDate movEstData) {
        this.movEstData = movEstData;
    }

    public String getMovEstTipo() {
        return this.movEstTipo;
    }

    public void setMovEstTipo(String movEstTipo) {
        this.movEstTipo = movEstTipo;
    }

    public Float getMovEstQtd() {
        return this.movEstQtd;
    }

    public void setMovEstQtd(Float movEstQtd) {
        this.movEstQtd = movEstQtd;
    }

    public Movimentacao getMovEstMovimentacao() {
        return this.movEstMovimentacao;
    }

    public void setMovEstMovimentacao(Movimentacao movEstMovimentacao) {
        this.movEstMovimentacao = movEstMovimentacao;
    }

    public Produto getMovEstProduto() {
        return this.movEstProduto;
    }

    public void setMovEstProduto(Produto movEstProduto) {
        this.movEstProduto = movEstProduto;
    }

    public Float getMovEstQtdPosterior() {
        return movEstQtdPosterior;
    }

    public void setMovEstQtdPosterior(Float movEstQtdPosterior) {
        this.movEstQtdPosterior = movEstQtdPosterior;
    }

    public Float getMovEstQtdAnterior() {
        return movEstQtdAnterior;
    }

    public void setMovEstQtdAnterior(Float movEstQtdAnterior) {
        this.movEstQtdAnterior = movEstQtdAnterior;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movEstId, movEstData, movEstTipo, movEstQtd, movEstMovimentacao, movEstProduto);
    }
}

package com.estoqueige.estoqueige.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.estoqueige.estoqueige.models.Movimentacao;

public class MovEstoqueDto {
    private Long movEstId;
    private LocalDate movEstData;
    private String movEstTipo;
    private Float movEstQtd;
    private Float movEstQtdAnterior;
    private Float movEstQtdPosterior;
    private Movimentacao movEstMovimentacao;


    public MovEstoqueDto() {
    }

    public MovEstoqueDto(Long movEstId, LocalDate movEstData, String movEstTipo, Float movEstQtd, Float movEstQtdAnterior, Float movEstQtdPosterior, Movimentacao movEstMovimentacao) {
        this.movEstId = movEstId;
        this.movEstData = movEstData;
        this.movEstTipo = movEstTipo;
        this.movEstQtd = movEstQtd;
        this.movEstQtdAnterior = movEstQtdAnterior;
        this.movEstQtdPosterior = movEstQtdPosterior;
        Movimentacao movimentacao = movEstMovimentacao;
        movimentacao.setProdutosMov(null);
        this.movEstMovimentacao = movimentacao;
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

    public Float getMovEstQtdAnterior() {
        return this.movEstQtdAnterior;
    }

    public void setMovEstQtdAnterior(Float movEstQtdAnterior) {
        this.movEstQtdAnterior = movEstQtdAnterior;
    }

    public Float getMovEstQtdPosterior() {
        return this.movEstQtdPosterior;
    }

    public void setMovEstQtdPosterior(Float movEstQtdPosterior) {
        this.movEstQtdPosterior = movEstQtdPosterior;
    }

    public Movimentacao getMovEstMovimentacao() {
        return this.movEstMovimentacao;
    }

    public void setMovEstMovimentacao(Movimentacao movEstMovimentacao) {
        this.movEstMovimentacao = movEstMovimentacao;
    }
    
    @Override
    public String toString() {
        return "{" +
            " movEstId='" + getMovEstId() + "'" +
            ", movEstData='" + getMovEstData() + "'" +
            ", movEstTipo='" + getMovEstTipo() + "'" +
            ", movEstQtd='" + getMovEstQtd() + "'" +
            ", movEstQtdAnterior='" + getMovEstQtdAnterior() + "'" +
            ", movEstQtdPosterior='" + getMovEstQtdPosterior() + "'" +
            ", movEstMovimentacao='" + getMovEstMovimentacao() + "'" +
            "}";
    }
    
    
}

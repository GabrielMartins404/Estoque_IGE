package com.estoqueige.estoqueige.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import com.estoqueige.estoqueige.models.enums.MovStatus;
import com.estoqueige.estoqueige.models.enums.MovTipo;
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

    @Column(name = "movDataHorario", nullable = false)
    @NotNull
    private LocalTime movEstHorario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovTipo movEstTipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovStatus movEstStatus;

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
    //@JsonIgnore
    private Movimentacao movEstMovimentacao;

    public MovimentacaoEstoque() {
    }

    public MovimentacaoEstoque(Long movEstId, LocalDate movEstData, LocalTime movEstHorario, MovTipo movEstTipo, MovStatus movEstStatus, Float movEstQtd, Float movEstQtdAnterior, Float movEstQtdPosterior, Produto movEstProduto, Movimentacao movEstMovimentacao) {
        this.movEstId = movEstId;
        this.movEstData = movEstData;
        this.movEstHorario = movEstHorario;
        this.movEstTipo = movEstTipo;
        this.movEstStatus = movEstStatus;
        this.movEstQtd = movEstQtd;
        this.movEstQtdAnterior = movEstQtdAnterior;
        this.movEstQtdPosterior = movEstQtdPosterior;
        this.movEstProduto = movEstProduto;
        //this.movEstMovimentacao = movEstMovimentacao;
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

    public LocalTime getMovEstHorario() {
        return this.movEstHorario;
    }

    public void setMovEstHorario(LocalTime movEstHorario) {
        this.movEstHorario = movEstHorario;
    }

    public MovTipo getMovEstTipo() {
        return this.movEstTipo;
    }

    public void setMovEstTipo(MovTipo movEstTipo) {
        this.movEstTipo = movEstTipo;
    }

    public MovStatus getMovEstStatus() {
        return this.movEstStatus;
    }

    public void setMovEstStatus(MovStatus movEstStatus) {
        this.movEstStatus = movEstStatus;
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

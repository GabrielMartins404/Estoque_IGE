package com.estoqueige.estoqueige.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = Movimentacao.TABLE_NAME)
public class Movimentacao {
    public static final String TABLE_NAME = "movimentacao";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movId", unique = true)
    private Long movId;

    @Column(name = "movData", nullable = false)
    @NotNull
    private LocalDate movData;

    @Column(name = "movDataCancelamento", nullable = true)
    private LocalDate movDataCancelamento;

    @Column(name = "movStatus", length = 2,nullable = false)
    @NotBlank
    private String movStatus;

    @Column(name = "movOrigem", length = 2,nullable = false)
    @NotBlank
    private String movOrigem;

    @Column(name = "movTipo", length = 2,nullable = false)
    @NotBlank
    private String movTipo;

    /* Chaves estrangeiras */

    @ManyToOne
    @JoinColumn(name = "movUsuario", nullable = false)
    private Usuario movUsuario;
    
    @ManyToOne
    @JoinColumn(name = "movRequisitante", nullable = false)
    private Requisitante movRequisitante;

    /*
    Nesse caso, a classe movimentação receberá uma lista de vários produtos, contudo, no banco de dados
    a ID da requisição deverá ser passado a movimentação
    */
    @OneToMany(mappedBy = "proMovMovimentacao", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProdutoMovimentacao> produtosMov;


    public Movimentacao() {
    }

    public Movimentacao(Long movId, LocalDate movData, LocalDate movDataCancelamento, String movStatus, String movOrigem, String movTipo, Usuario movUsuario, Requisitante movRequisitante, List<ProdutoMovimentacao> produtosMov) {
        this.movId = movId;
        this.movData = movData;
        this.movDataCancelamento = movDataCancelamento;
        this.movStatus = movStatus;
        this.movOrigem = movOrigem;
        this.movTipo = movTipo;
        this.movUsuario = movUsuario;
        this.movRequisitante = movRequisitante;
        this.produtosMov = produtosMov;
    }

    public Movimentacao getReferencia() {
        Movimentacao referencia = new Movimentacao();
        referencia.setMovId(this.movId);
        referencia.setMovData(this.movData);
        referencia.setMovDataCancelamento(this.movDataCancelamento);
        referencia.setMovStatus(this.movStatus);
        referencia.setMovOrigem(this.movOrigem);
        referencia.setMovTipo(this.movTipo);
        referencia.setMovUsuario(this.movUsuario);
        referencia.setMovRequisitante(this.movRequisitante);
        
        return referencia;
    }

    public Long getMovId() {
        return this.movId;
    }

    public void setMovId(Long movId) {
        this.movId = movId;
    }

    public LocalDate getMovData() {
        return this.movData;
    }

    public void setMovData(LocalDate movData) {
        this.movData = movData;
    }

    public LocalDate getMovDataCancelamento() {
        return this.movDataCancelamento;
    }

    public void setMovDataCancelamento(LocalDate movDataCancelamento) {
        this.movDataCancelamento = movDataCancelamento;
    }

    public String getMovStatus() {
        return this.movStatus;
    }

    public void setMovStatus(String movStatus) {
        this.movStatus = movStatus;
    }

    public String getMovOrigem() {
        return this.movOrigem;
    }

    public void setMovOrigem(String movOrigem) {
        this.movOrigem = movOrigem;
    }

    public String getMovTipo() {
        return this.movTipo;
    }

    public void setMovTipo(String movTipo) {
        this.movTipo = movTipo;
    }

    public Usuario getMovUsuario() {
        return this.movUsuario;
    }

    public void setMovUsuario(Usuario movUsuario) {
        this.movUsuario = movUsuario;
    }

    public Requisitante getMovRequisitante() {
        return this.movRequisitante;
    }

    public void setMovRequisitante(Requisitante movRequisitante) {
        this.movRequisitante = movRequisitante;
    }

    public List<ProdutoMovimentacao> getProdutosMov() {
        return this.produtosMov;
    }

    public void setProdutosMov(List<ProdutoMovimentacao> produtosMov) {
        this.produtosMov = produtosMov;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movId, movData, movDataCancelamento, movStatus, movOrigem, movTipo, movUsuario, movRequisitante, produtosMov);
    }

}

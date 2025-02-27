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

@Entity
@Table(name = Requisicao.TABLE_NAME)
public class Requisicao {
    public static final String TABLE_NAME = "requisicao";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reqId", unique = true)
    private Long reqId;

    @Column(name = "reqData", nullable = false)
    @NotNull
    private LocalDate reqData;

    @Column(name = "reqDataCancelamento", nullable = true)
    private LocalDate reqDataCancelamento;

    @Column(name = "reqStatus", length = 2,nullable = false)
    @NotBlank
    private String reqStatus;

    @Column(name = "isAnonima", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isAnonima;

    /* Chaves estrangeiras */

    @ManyToOne
    @JoinColumn(name = "reqUsuario", nullable = true)
    private Usuario reqUsuario;
    
    @ManyToOne
    @JoinColumn(name = "reqRequisitante", nullable = true)
    private Requisitante reqRequisitante;

    @OneToMany(mappedBy = "proMovRequisicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoMovimentacao> proMovRequisicao;


    public Requisicao() {
    }

    public Requisicao(Long reqId, LocalDate reqData, LocalDate reqDataCancelamento, String reqStatus, Boolean isAnonima, Usuario reqUsuario, Requisitante reqRequisitante) {
        this.reqId = reqId;
        this.reqData = reqData;
        this.reqDataCancelamento = reqDataCancelamento;
        this.reqStatus = reqStatus;
        this.isAnonima = isAnonima;
        this.reqUsuario = reqUsuario;
        this.reqRequisitante = reqRequisitante;
    }

    public Long getReqId() {
        return this.reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public LocalDate getReqData() {
        return this.reqData;
    }

    public void setReqData(LocalDate reqData) {
        this.reqData = reqData;
    }

    public LocalDate getReqDataCancelamento() {
        return this.reqDataCancelamento;
    }

    public void setReqDataCancelamento(LocalDate reqDataCancelamento) {
        this.reqDataCancelamento = reqDataCancelamento;
    }

    public String getReqStatus() {
        return this.reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

    public Boolean isIsAnonima() {
        return this.isAnonima;
    }

    public Boolean getIsAnonima() {
        return this.isAnonima;
    }

    public void setIsAnonima(Boolean isAnonima) {
        this.isAnonima = isAnonima;
    }

    public Usuario getReqUsuario() {
        return this.reqUsuario;
    }

    public void setReqUsuario(Usuario reqUsuario) {
        this.reqUsuario = reqUsuario;
    }

    public Requisitante getReqRequisitante() {
        return this.reqRequisitante;
    }

    public void setReqRequisitante(Requisitante reqRequisitante) {
        this.reqRequisitante = reqRequisitante;
    }

    public List<ProdutoMovimentacao> getProReqRequisicao() {
        return this.proMovRequisicao;
    }

    public void setProReqRequisicao(List<ProdutoMovimentacao> proReqRequisicao) {
        this.proMovRequisicao = proReqRequisicao;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reqId, reqData, reqDataCancelamento, reqStatus, isAnonima, reqUsuario, reqRequisitante, proMovRequisicao);
    }
    
}

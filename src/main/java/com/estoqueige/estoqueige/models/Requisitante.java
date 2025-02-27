package com.estoqueige.estoqueige.models;

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
@Table(name = Requisitante.TABLE_NAME)
public class Requisitante {
    public static final String TABLE_NAME = "requisitante";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reqId", unique = true)
    private Long reqId;

    @Column(name = "reqNome", length = 100, nullable = false)
    @NotBlank
    private String reqNome;

    @Column(name = "isAtivo", columnDefinition = "TINYINT(1) DEFAULT 1")
    @NotNull
    private Boolean isAtivo;

    /* Anotações das chaves estrangeiras */
    @ManyToOne
    @JoinColumn(name = "facRequisitante", nullable = true)
    private Faculdade facRequisitante;

    public Requisitante() {
    }

    public Requisitante(Long reqId, String reqNome, Boolean isAtivo, Faculdade facRequisitante) {
        this.reqId = reqId;
        this.reqNome = reqNome;
        this.isAtivo = isAtivo;
        this.facRequisitante = facRequisitante;
    }

    public Long getReqId() {
        return this.reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public String getReqNome() {
        return this.reqNome;
    }

    public void setReqNome(String reqNome) {
        this.reqNome = reqNome;
    }

    public Boolean isAtivo() {
        return this.isAtivo;
    }

    public Boolean getIsAtivo() {
        return this.isAtivo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public Faculdade getFacRequisitante() {
        return this.facRequisitante;
    }

    public void setFacRequisitante(Faculdade facRequisitante) {
        this.facRequisitante = facRequisitante;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reqId, reqNome, isAtivo, facRequisitante);
    }
    
}

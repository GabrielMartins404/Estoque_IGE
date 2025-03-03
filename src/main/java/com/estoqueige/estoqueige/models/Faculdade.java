package com.estoqueige.estoqueige.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = Faculdade.TABLE_NAME)
public class Faculdade {
    public static final String TABLE_NAME = "faculdade";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facId", unique = true)
    private Long facId;

    @Column(name = "facNome", length = 50, nullable = false)
    @NotBlank(message = "O nome não pode ser nulo nem vazio")
    private String facNome;

    @Column(name = "isAtivo", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isAtivo;

    /* Anotações das chaves estrangeiras */
    @OneToMany(mappedBy = "facRequisitante")
    @JsonBackReference
    private List<Requisitante> requisitantes = new ArrayList<>(); 


    public Faculdade() {
    }

    public Faculdade(Long facId, String facNome, Boolean isAtivo) {
        this.facId = facId;
        this.facNome = facNome;
        this.isAtivo = isAtivo;
    }

    public Long getFacId() {
        return this.facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public String getFacNome() {
        return this.facNome;
    }

    public void setFacNome(String facNome) {
        this.facNome = facNome;
    }

    public Boolean isIsAtivo() {
        return this.isAtivo;
    }

    public Boolean getIsAtivo() {
        return this.isAtivo;
    }

    public void setIsAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facId, facNome, isAtivo);
    }
  
}

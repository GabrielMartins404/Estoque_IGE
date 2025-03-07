package com.estoqueige.estoqueige.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = Produto.TABLE_NAME)
public class Produto {
    public static final String TABLE_NAME = "produto"; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proId", unique = true)
    private Long proId;

    @Column(name = "proNome", length = 100, nullable = false)
    @NotBlank
    private String proNome;

    @Column(name = "proSipac", length = 30, nullable = false)
    @NotBlank
    private String proSipac;

    @Column(name = "proQtd", nullable = false, columnDefinition = "FLOAT DEFAULT 0")
    @NotNull
    private Float proQtd;

    @Column(name = "isAtivo", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean isAtivo;

    /* Definição das chaves estrangeiras */
    @OneToMany(mappedBy = "proMovProduto")
    //@JsonManagedReference // Definição do lado "pai"
    private List<ProdutoMovimentacao> produtoMovimentacoes = new ArrayList<>();

    @OneToMany(mappedBy = "proReqProduto")
    //@JsonManagedReference // Definição do lado "pai"
    private List<ProdutoRequisicao> produtoRequisicoes = new ArrayList<>();

    @OneToMany(mappedBy = "movEstProduto")
    //@JsonManagedReference // Definição do lado "pai"
    private List<MovimentacaoEstoque> movimentacaoEstoque = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long proId, String proNome, String proSipac, Float proQtd, Boolean isAtivo, List<MovimentacaoEstoque> movimentacaoEstoque) {
        this.proId = proId;
        this.proNome = proNome;
        this.proSipac = proSipac;
        this.proQtd = proQtd;
        this.isAtivo = isAtivo;
        this.movimentacaoEstoque = new ArrayList<>();
        this.produtoMovimentacoes = new ArrayList<>();
        this.produtoRequisicoes = new ArrayList<>();
    }

    public Long getProId() {
        return this.proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return this.proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public String getProSipac() {
        return this.proSipac;
    }

    public void setProSipac(String proSipac) {
        this.proSipac = proSipac;
    }

    public Float getProQtd() {
        return this.proQtd;
    }

    public void setProQtd(Float proQtd) {
        this.proQtd = proQtd;
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

    public List<MovimentacaoEstoque> getMovimentacaoEstoque() {
        return movimentacaoEstoque;
    }

    public void setMovimentacaoEstoque(List<MovimentacaoEstoque> movimentacaoEstoque) {
        this.movimentacaoEstoque = movimentacaoEstoque;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proId, proNome, proSipac, proQtd, isAtivo);
    }
    
}

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name = Produto.TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ProdutoMovimentacao> produtoMovimentacoes = new ArrayList<>();

    @OneToMany(mappedBy = "proReqProduto")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<ProdutoRequisicao> produtoRequisicoes = new ArrayList<>();

    @OneToMany(mappedBy = "movEstProduto")
    private List<MovimentacaoEstoque> movimentacaoEstoque = new ArrayList<>();

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

    
}

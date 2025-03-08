package com.estoqueige.estoqueige.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import com.estoqueige.estoqueige.models.enums.MovOrigem;
import com.estoqueige.estoqueige.models.enums.MovStatus;
import com.estoqueige.estoqueige.models.enums.MovTipo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = Movimentacao.TABLE_NAME)
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "movId"
)
public class Movimentacao {
    public static final String TABLE_NAME = "movimentacao";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movId", unique = true)
    private Long movId;

    @Column(name = "movData", nullable = false)
    @NotNull
    private LocalDate movData;

    @Column(name = "movDataHorario", nullable = false)
    @NotNull
    private LocalTime movHorario;

    @Column(name = "movDataCancelamento", nullable = true)
    private LocalDate movDataCancelamento;

    @Column(name = "movHorarioCancelamento", nullable = true)
    private LocalTime movHorarioCancelamento;

    //Aqui uso Enums, que estão no pacote de enums
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovStatus movStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovTipo movTipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovOrigem movOrigem;

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
    @JsonBackReference // Definição do lado "pai"
    private List<ProdutoMovimentacao> produtosMov;


    public Movimentacao() {
    }

    public Movimentacao(Long movId, LocalDate movData, LocalDate movDataCancelamento, MovStatus movStatus, MovOrigem movOrigem, MovTipo movTipo, Usuario movUsuario, Requisitante movRequisitante, List<ProdutoMovimentacao> produtosMov) {
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

    public LocalTime getMovHorario() {
        return this.movHorario;
    }

    public void setMovHorario(LocalTime movHorario) {
        this.movHorario = movHorario;
    }

    public LocalDate getMovDataCancelamento() {
        return this.movDataCancelamento;
    }

    public void setMovDataCancelamento(LocalDate movDataCancelamento) {
        this.movDataCancelamento = movDataCancelamento;
    }

    public LocalTime getMovHorarioCancelamento() {
        return this.movHorarioCancelamento;
    }

    public void setMorarioCancelamento(LocalTime movHorarioCancelamento) {
        this.movHorarioCancelamento = movHorarioCancelamento;
    }

    public MovStatus getMovStatus() {
        return this.movStatus;
    }

    public void setMovStatus(MovStatus movStatus) {
        this.movStatus = movStatus;
    }

    public MovOrigem getMovOrigem() {
        return this.movOrigem;
    }

    public void setMovOrigem(MovOrigem movOrigem) {
        this.movOrigem = movOrigem;
    }

    public MovTipo getMovTipo() {
        return this.movTipo;
    }

    public void setMovTipo(MovTipo movTipo) {
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

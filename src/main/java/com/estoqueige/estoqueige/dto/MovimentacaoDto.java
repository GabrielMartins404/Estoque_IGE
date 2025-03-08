package com.estoqueige.estoqueige.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.estoqueige.estoqueige.models.ProdutoMovimentacao;
import com.estoqueige.estoqueige.models.Requisitante;
import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.models.enums.MovOrigem;
import com.estoqueige.estoqueige.models.enums.MovStatus;
import com.estoqueige.estoqueige.models.enums.MovTipo;

import java.util.Objects;

//Essas classe serve unicamente para me auxiliar no retorno ao front
public class MovimentacaoDto {
    private Long movId;

    private LocalDate movData;

    private LocalDate movDataCancelamento;

    private LocalTime movHorario;

    private LocalTime movHorarioCancelamento;

    private MovStatus movStatus;

    private MovOrigem movOrigem;

    private MovTipo movTipo;

    private Usuario movUsuario;

    private Requisitante movRequisitante;

    private List<ProdutoMovimentacaoDto> produtosMov;

    public MovimentacaoDto() {
    }

    public MovimentacaoDto(Long movId, LocalDate movData, LocalDate movDataCancelamento, MovStatus movStatus, MovOrigem movOrigem, MovTipo movTipo, Usuario movUsuario, Requisitante movRequisitante, List<ProdutoMovimentacaoDto> produtosMov) {
        this.movId = movId;
        this.movData = movData;
        this.movDataCancelamento = movDataCancelamento;
        this.movStatus = movStatus;
        this.movOrigem = movOrigem;
        this.movTipo = movTipo;
        this.movUsuario = movUsuario;
        this.movRequisitante = movRequisitante;
        this.produtosMov = null;
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

    public LocalTime getMovHorarioCancelamento() {
        return this.movHorarioCancelamento;
    }

    public void setMorarioCancelamento(LocalTime movHorarioCancelamento) {
        this.movHorarioCancelamento = movHorarioCancelamento;
    }

    public void setMovDataCancelamento(LocalDate movDataCancelamento) {
        this.movDataCancelamento = movDataCancelamento;
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

    public List<ProdutoMovimentacaoDto> getProdutosMov() {
        return this.produtosMov;
    }

    public void setProdutosMov(List<ProdutoMovimentacaoDto> produtosMov) {
        this.produtosMov = produtosMov;
    }

    @Override
    public String toString() {
        return "{" +
            " movId='" + getMovId() + "'" +
            ", movData='" + getMovData() + "'" +
            ", movDataCancelamento='" + getMovDataCancelamento() + "'" +
            ", movStatus='" + getMovStatus() + "'" +
            ", movOrigem='" + getMovOrigem() + "'" +
            ", movTipo='" + getMovTipo() + "'" +
            ", movUsuario='" + getMovUsuario() + "'" +
            ", movRequisitante='" + getMovRequisitante() + "'" +
            ", produtosMov='" + getProdutosMov() + "'" +
            "}";
    }

    
}

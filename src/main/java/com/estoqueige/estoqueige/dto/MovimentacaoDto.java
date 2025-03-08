package com.estoqueige.estoqueige.dto;

import java.time.LocalDate;
import java.util.List;

import com.estoqueige.estoqueige.models.ProdutoMovimentacao;
import com.estoqueige.estoqueige.models.Requisitante;
import com.estoqueige.estoqueige.models.Usuario;
import java.util.Objects;

//Essas classe serve unicamente para me auxiliar no retorno ao front
public class MovimentacaoDto {
    private Long movId;

    private LocalDate movData;

    private LocalDate movDataCancelamento;

    private String movStatus;

    private String movOrigem;

    private String movTipo;

    private Usuario movUsuario;

    private Requisitante movRequisitante;

    private List<ProdutoMovimentacaoDto> produtosMov;

    public MovimentacaoDto() {
    }

    public MovimentacaoDto(Long movId, LocalDate movData, LocalDate movDataCancelamento, String movStatus, String movOrigem, String movTipo, Usuario movUsuario, Requisitante movRequisitante, List<ProdutoMovimentacaoDto> produtosMov) {
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

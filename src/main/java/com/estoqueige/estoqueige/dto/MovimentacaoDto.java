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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

//Essas classe serve unicamente para me auxiliar no retorno ao front
@Getter
@Setter
@AllArgsConstructor
public class MovimentacaoDto {
    private Long movId;

    private LocalDate movData;

    private LocalDate movDataCancelamento;

    private LocalTime movHorario;

    private LocalTime movHorarioCancelamento;

    private Long movNf;

    private Long movNumRequisicao;

    private MovStatus movStatus;

    private MovOrigem movOrigem;

    private MovTipo movTipo;

    private Usuario movUsuario;

    private Requisitante movRequisitante;

    private List<ProdutoMovimentacaoDto> produtosMov = null;

    public MovimentacaoDto() {
    }

    // public MovimentacaoDto(Long movId, LocalDate movData, LocalDate movDataCancelamento, MovStatus movStatus, MovOrigem movOrigem, MovTipo movTipo, Usuario movUsuario, Requisitante movRequisitante, List<ProdutoMovimentacaoDto> produtosMov) {
    //     this.movId = movId;
    //     this.movData = movData;
    //     this.movDataCancelamento = movDataCancelamento;
    //     this.movStatus = movStatus;
    //     this.movOrigem = movOrigem;
    //     this.movTipo = movTipo;
    //     this.movUsuario = movUsuario;
    //     this.movRequisitante = movRequisitante;
    //     this.produtosMov = null;
    // }

}

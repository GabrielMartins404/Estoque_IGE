package com.estoqueige.estoqueige.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
    private Long proId;
    private String proNome;
    private String proSipac;
    private Float proQtd;
    private Boolean isAtivo;

    public ProdutoDto() {
    }

    public ProdutoDto(Long proId, String proNome, String proSipac, Float proQtd, Boolean isAtivo) {
        this.proId = proId;
        this.proNome = proNome;
        this.proSipac = proSipac;
        this.proQtd = proQtd;
        this.isAtivo = isAtivo;
        
    }
    
}

package com.estoqueige.estoqueige.dto;

import java.util.List;
import java.util.Objects;

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
    
}

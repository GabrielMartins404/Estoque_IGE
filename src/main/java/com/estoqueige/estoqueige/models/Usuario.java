package com.estoqueige.estoqueige.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = Usuario.TABLE_NAME)
public class Usuario {
    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuId", unique = true)
    private Long usuId;

    @Column(name = "usuNome", length = 100, nullable = false)
    @NotBlank
    private String usuNome;

    @Column(name = "usuLogin", length = 60, nullable = false, unique = true)
    @NotBlank
    private String usuLogin;

    @Column(name = "usuSenha", length = 30, nullable = false)
    @NotBlank
    private String usuSenha;

    @Column(name = "isAtivo", columnDefinition = "TINYINT(1) DEFAULT 1")
    @NotNull
    private Boolean isAtivo;

    @Column(name = "isAdmin", columnDefinition = "TINYINT(1) DEFAULT 0")
    @NotNull
    private Boolean isAdmin;


    public Usuario() {
    }

    public Usuario(Long usuId, String usuNome, String usuLogin, String usuSenha, Boolean isAtivo, Boolean isAdmin) {
        this.usuId = usuId;
        this.usuNome = usuNome;
        this.usuLogin = usuLogin;
        this.usuSenha = usuSenha;
        this.isAtivo = isAtivo;
        this.isAdmin = isAdmin;
    }

    public Long getUsuId() {
        return this.usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuNome() {
        return this.usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuLogin() {
        return this.usuLogin;
    }

    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuSenha() {
        return this.usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
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

    public Boolean isIsAdmin() {
        return this.isAdmin;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuId, usuNome, usuLogin, usuSenha, isAtivo, isAdmin);
    }

}

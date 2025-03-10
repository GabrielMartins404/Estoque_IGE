package com.estoqueige.estoqueige.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Usuario.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
}

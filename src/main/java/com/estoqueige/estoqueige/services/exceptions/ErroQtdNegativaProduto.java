package com.estoqueige.estoqueige.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ErroQtdNegativaProduto extends RuntimeException{
    public ErroQtdNegativaProduto(String mensagem){
        super(mensagem);
    }
}

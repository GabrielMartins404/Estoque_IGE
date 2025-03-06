package com.estoqueige.estoqueige.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.estoqueige.estoqueige.models.Faculdade;
import com.estoqueige.estoqueige.services.FaculdadeServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Validated
@RequestMapping("/faculdade")
public class FaculdadeController {
    private final FaculdadeServices faculdadeServices;

    public FaculdadeController(FaculdadeServices faculdadeServices) {
        this.faculdadeServices = faculdadeServices;
    }

    @GetMapping("/")
    public ResponseEntity<List<Faculdade>> buscarFaculdades() {
        List<Faculdade> faculdades = this.faculdadeServices.buscarTodasFaculdades();
        return ResponseEntity.ok().body(faculdades);
    }
    
}

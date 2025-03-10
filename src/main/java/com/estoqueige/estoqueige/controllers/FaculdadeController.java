package com.estoqueige.estoqueige.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueige.estoqueige.models.Faculdade;
import com.estoqueige.estoqueige.services.FaculdadeServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Validated
@RequestMapping("/faculdade")
public class FaculdadeController {
    private final FaculdadeServices faculdadeServices;

    public FaculdadeController(FaculdadeServices faculdadeServices) {
        this.faculdadeServices = faculdadeServices;
    }

    @GetMapping("/{idFaculdade}")
    public ResponseEntity<Faculdade> buscarFaculdadesPorId(@PathVariable Long idFaculdade) {
        Faculdade faculdade = this.faculdadeServices.buscarFaculdadePorId(idFaculdade);
        return ResponseEntity.ok().body(faculdade);
    }

    @GetMapping("/")
    public ResponseEntity<List<Faculdade>> buscarFaculdades() {
        List<Faculdade> faculdades = this.faculdadeServices.buscarTodasFaculdades();
        return ResponseEntity.ok().body(faculdades);
    }

    @PostMapping("/")
    public ResponseEntity<Faculdade> criarFaculdade(@RequestBody Faculdade faculdade){
        this.faculdadeServices.cadastrarFaculdade(faculdade);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idFaculdade}").buildAndExpand(faculdade.getFacId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idFaculdade}")
    public ResponseEntity<Faculdade> atualizarFaculdade(@RequestBody Faculdade faculdade, @PathVariable Long idFaculdade){
        faculdade.setFacId(idFaculdade);
        return ResponseEntity.ok(this.faculdadeServices.atualizarFaculdade(faculdade));
    }

    @PutMapping("/inativar/{idFaculdade}")
    public ResponseEntity<Void> inativarFaculdade(@PathVariable Long idFaculdade){
        this.faculdadeServices.alterarStatusAtivoFaculdade(idFaculdade);
        return ResponseEntity.noContent().build();
    }
    
}

package com.estoqueige.estoqueige.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueige.estoqueige.dto.MovimentacaoDto;
import com.estoqueige.estoqueige.models.Movimentacao;
import com.estoqueige.estoqueige.services.MovimentacaoServices;

@RestController
@Validated
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    private final MovimentacaoServices movimentacaoServices;

    public MovimentacaoController(MovimentacaoServices movimentacaoServices) {
        this.movimentacaoServices = movimentacaoServices;
    }

    @GetMapping("/{idMovimentacao}")
    public ResponseEntity<MovimentacaoDto> buscarMovimentacaosPorId(@PathVariable Long idMovimentacao) {
        MovimentacaoDto movimentacao = this.movimentacaoServices.retornarMovimentacaoDto(idMovimentacao);
        return ResponseEntity.ok().body(movimentacao);
    }

    @GetMapping("/")
    public ResponseEntity<List<MovimentacaoDto>> buscarMovimentacaos() {
        List<MovimentacaoDto> movimentacao = this.movimentacaoServices.buscarTodasMovimentacoes();
        return ResponseEntity.ok().body(movimentacao);
    }

    @PostMapping("/")
    public ResponseEntity<Movimentacao> criarMovimentacao(@RequestBody Movimentacao movimentacao){
        this.movimentacaoServices.salvarMovimentacao(movimentacao);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idMovimentacao}").buildAndExpand(movimentacao.getMovId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}

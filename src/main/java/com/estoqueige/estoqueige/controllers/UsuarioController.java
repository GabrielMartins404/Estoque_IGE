package com.estoqueige.estoqueige.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.services.UsuarioServices;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuariosPorId(@PathVariable Long idUsuario) {
        Usuario usuario = this.usuarioServices.buscarUsuarioPorId(idUsuario);
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> buscarUsuarios() {
        List<Usuario> usuarios = this.usuarioServices.buscarTodosUsuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody Usuario usuario){
        this.usuarioServices.cadastrarUsuario(usuario);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idUsuario}").buildAndExpand(usuario.getUsuId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Long idUsuario){
        usuario.setUsuId(idUsuario);
        return ResponseEntity.ok(this.usuarioServices.atualizarUsuario(usuario));
    }

    @PutMapping("/inativar/{idUsuario}")
    public ResponseEntity<Void> inativarUsuario(@Valid @PathVariable Long idUsuario){
        this.usuarioServices.alterarStatusAtivoUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
